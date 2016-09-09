package demo.lieeber.com.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by lieeber on 16/9/9.
 */

public class LBCalendarView extends RecyclerView {

    private int minMonth = new DateTime().getMonthOfYear();
    private int maxMonth = new DateTime().getMonthOfYear();
    private int minYear = new DateTime().getYear();
    private int maxYear = new DateTime().getYear();
    private MyRecyclerViewAdapter mAdapter;

    public LBCalendarView(Context context) {
        super(context);
        initVeiw();
    }


    public LBCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initVeiw();
    }

    private void initVeiw() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
        setLayoutManager(gridLayoutManager);
        mAdapter = new MyRecyclerViewAdapter();
        setAdapter(mAdapter);
        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                Object item = mAdapter.getItem(position);
                if (item instanceof DateTime) {
                    return 1;
                } else {
                    return 7;
                }
            }
        });
        setData();
    }

    private void setData() {
        DateTime startDate = new DateTime(minYear, minMonth, 1, 0, 0);
        while (startDate.getDayOfMonth() != 1) {
            startDate = startDate.minusDays(1);
        }
        ArrayList monthList = new ArrayList<>();
        for (int i = 0; i < maxMonth - minMonth + 1; i++) {
            monthList.add(new MonthModel(startDate.monthOfYear().getAsShortText()));
            int dayOfWeek = startDate.getDayOfWeek();
            for (int j = 1; j < dayOfWeek; j++) {
                monthList.add(new DateTime(2000, 1, 1, 1, 1));
            }
            for (int j = 0; j < 42; j++) {
                monthList.add(startDate);
                DateTime tempDate = startDate.plusDays(1);
                if (startDate.getMonthOfYear() != tempDate.getMonthOfYear()) {
                    addSurplusItem(monthList, startDate);
                    startDate = tempDate;
                    break;
                } else {
                    startDate = tempDate;
                }
            }
        }
        mAdapter.setData(monthList);
    }


    private void addSurplusItem(ArrayList monthList, DateTime startDate) {
        for (int i = 7; i > startDate.getDayOfWeek(); i--) {
            monthList.add(new DateTime(2000, 1, 1, 1, 1));
        }
    }


    public void setYearAndMonth(int minYear, int minMonth, int maxYear, int maxMonth) {
        if (minYear > maxYear) {
            Toast.makeText(getContext(), "最小年份不能大于最大年份", Toast.LENGTH_SHORT).show();
            return;
        }

        if (minMonth > maxMonth) {
            Toast.makeText(getContext(), "最小月份不能大于最大月份", Toast.LENGTH_SHORT).show();
            return;
        }
        this.minMonth = minMonth;
        this.minYear = minYear;
        this.maxYear = maxYear;
        this.maxMonth = maxMonth;
        setData();
    }
}
