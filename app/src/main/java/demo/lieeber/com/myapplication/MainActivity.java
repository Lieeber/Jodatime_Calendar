package demo.lieeber.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,7);
        recyclerView.setLayoutManager(gridLayoutManager);
        final MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
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
        DateTime dateTime = new DateTime();
        DateTime startDate = dateTime.minusMonths(8);
        while (startDate.getDayOfMonth() != 1) {
            startDate = startDate.minusDays(1);
        }
        ArrayList monthList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            monthList.add(new MonthModel(startDate.monthOfYear().getAsShortText()));
            int dayOfWeek = startDate.getDayOfWeek();
            for (int j = 1; j < dayOfWeek; j++) {
                monthList.add(new DateTime(2000, 1, 1, 1, 1));
            }
            for (int j = 0; j < 42; j++) {
                monthList.add(startDate);
                DateTime tempDate = startDate.plusDays(1);
                if (startDate.getMonthOfYear() != tempDate.getMonthOfYear()) {
                    addSurplusItem(monthList,startDate);
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
}
