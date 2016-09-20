package demo.lieeber.com.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import demo.lieeber.com.myapplication.CalendarViewAdapter.CellClickListener;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lieeber on 16/9/9.
 */

public class LBCalendarView extends RecyclerView {
    private int minMonth = new DateTime().getMonthOfYear();
    private int maxMonth = new DateTime().getMonthOfYear();
    private int minYear = new DateTime().getYear();
    private int maxYear = new DateTime().getYear();
    private CalendarViewAdapter mAdapter;
    private boolean loading;
    private int lastVisibleItem;

    public LBCalendarView(Context context) {
        super(context);
        initVeiw();
    }

    public LBCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initVeiw();
    }

    public void update() {
        mAdapter.notifyDataSetChanged();
    }


    public interface ItemClickListener {
        void onItemClick(ScheduleDate scheduleDate);
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface LoadMoreListener {
        void loadMore();
    }

    private LoadMoreListener loadMoreListener;

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    private void initVeiw() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
        setLayoutManager(gridLayoutManager);
        addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new CalendarViewAdapter();
        setAdapter(mAdapter);
        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                Object item = mAdapter.getItem(position);
                if (item instanceof ScheduleDate) {
                    return 1;
                } else {
                    return 7;
                }
            }
        });

        mAdapter.setOnCellClickListener(new CellClickListener() {
            @Override public void onCellClick(ScheduleDate scheduleDate) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(scheduleDate);
                }
            }
        });
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    if (loadMoreListener != null && loading == false) {
                        loadMoreListener.loadMore();
                    }
                    loading = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    public void loadMoreCompleted() {
        loading = false;
    }

    private void setCalendarData() {
        setCalendarData(null);
    }

    public void setCalendarData(final List<ScheduleDate> selectList) {
        Observable.create(new OnSubscribe<ArrayList>() {
            @Override
            public void call(Subscriber<? super ArrayList> subscriber) {
                DateTime startDate = new DateTime(minYear, minMonth, 1, 0, 0);
                ArrayList monthList = new ArrayList();
                DateTime minDate = new DateTime(minYear, minMonth, 1, 0, 0);
                DateTime maxDate = new DateTime(maxYear, maxMonth, 1, 0, 0);
                int monthSpace = Months.monthsBetween(minDate, maxDate).getMonths();
                for (int i = 0; i < monthSpace; i++) {
                    monthList.add(new MonthTitleModel(startDate.year().getAsShortText(Locale.getDefault()), startDate.monthOfYear().getAsShortText(Locale.getDefault())));
                    int dayOfWeek = startDate.getDayOfWeek();
                    for (int j = 1; j < dayOfWeek; j++) {
                        ScheduleDate scheduleDate = new ScheduleDate();
                        scheduleDate.isSpace = true;
                        monthList.add(scheduleDate);
                    }
                    for (int j = 0; j < 42; j++) {
                        if (selectList != null) {
                            setSelectCell(startDate, monthList, selectList);
                        } else {
                            monthList.add(new ScheduleDate(startDate, false, 0));
                        }
                        DateTime tempDate = startDate.plusDays(1);
                        if (startDate.getMonthOfYear() != tempDate.getMonthOfYear()) {
//                    addSurplusItem(monthList, startDate);
                            startDate = tempDate;
                            break;
                        } else {
                            startDate = tempDate;
                        }
                    }
                }
                subscriber.onNext(monthList);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList>() {
                    @Override public void call(ArrayList monthList) {
                        mAdapter.setData(monthList);
                    }
                });
    }

    /**
     * 设置需要显示的最大月份和最小月份
     */
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
        setCalendarData();
    }

    /**
     * 从当前月份开始 往后显示月份
     *
     * @param monthNum 往后显示的月份数
     */
    public void setStartByNowMonth(int monthNum) {
        this.minYear = new DateTime().getYear();
        this.minMonth = new DateTime().getMonthOfYear();
        this.maxMonth = new DateTime().plusMonths(monthNum).getMonthOfYear();
        this.maxYear = new DateTime().plusMonths(monthNum).getYear();
        setCalendarData();
    }

    /**
     * 月份往前添加
     *
     * @param month 往前添加的月份天数
     */
    public void plusPreMonth(int month) {
        DateTime dateTime = new DateTime(minYear, minMonth, 1, 0, 0).minusMonths(month);
        minYear = dateTime.getYear();
        minMonth = dateTime.getMonthOfYear();
    }

    /**
     * 月份往后添加
     *
     * @param month 往后添加的月份天数
     */
    public void plusPostMonth(int month) {
        DateTime dateTime = new DateTime(maxYear, maxMonth, 1, 0, 0).plusMonths(month);
        maxYear = dateTime.getYear();
        maxMonth = dateTime.getMonthOfYear();
    }

    public int getFirstMonth() {
        return minMonth;
    }

    //获取第一个月之前的一个月
    public int getPreFirstMonth() {
        DateTime dateTime = new DateTime(minYear, minMonth, 1, 0, 0).minusMonths(1);
        return dateTime.getMonthOfYear();
    }

    //获取第一个月之前的月份所对应的年份
    public int getPreYearOfFirstMonth() {
        DateTime dateTime = new DateTime(minYear, minMonth, 1, 0, 0).minusMonths(1);
        return dateTime.getYear();
    }

    //获取第一个月的月份
    public int getYearOfFirstMonth() {
        return minYear;
    }

    //获取最后一个月之后的一个月
    public int getPosLastMonth() {
        DateTime dateTime = new DateTime(maxYear, maxMonth, 1, 0, 0).plusMonths(1);
        return dateTime.getMonthOfYear();
    }

    //获取最后一个月之后的一个月对应的年份
    public int getPosYearOfLastMonth() {
        DateTime dateTime = new DateTime(maxYear, maxMonth, 1, 0, 0).plusMonths(1);
        return dateTime.getYear();
    }

    public void setSelectCell(DateTime startDate, ArrayList monthList, List<ScheduleDate> selectList) {
        for (int i = 0; i < selectList.size(); i++) {
            ScheduleDate selectItem = selectList.get(i);
            if (selectItem.dateTime.getYear() == startDate.getYear() && selectItem.dateTime.getDayOfYear() == startDate.getDayOfYear()) {
                DateTime nowDate = new DateTime();
                if (Days.daysBetween(selectItem.dateTime, nowDate).getDays() > 0) {
                    selectItem.isFinished = true;
                } else {
                    selectItem.isFinished = false;
                }
                monthList.add(selectItem);
                return;
            }
        }
        monthList.add(new ScheduleDate(startDate, false, 0));
    }

    private void addSurplusItem(ArrayList monthList, DateTime startDate) {
        for (int i = 7; i > startDate.getDayOfWeek(); i--) {
            monthList.add(new DateTime(2000, 1, 1, 1, 1));
        }
    }
}
