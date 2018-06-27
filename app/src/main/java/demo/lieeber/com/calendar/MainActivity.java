package demo.lieeber.com.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import demo.lieeber.com.calendar.LBCalendarView.ItemClickListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LBCalendarView calendarView = (LBCalendarView) findViewById(R.id.calendar_view);
        calendarView.setYearAndMonth(2018, 6, 2018, 12);
        calendarView.plusPreMonth(10);

        List<ScheduleDate> dateList = new ArrayList<>();

        DateTime dateTime = new DateTime(2018, 6, 1, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 1));

        dateTime = new DateTime(2018, 6, 7, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 0));

        dateTime = new DateTime(2018, 7, 11, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 1));

        dateTime = new DateTime(2018, 8, 9, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 1));

        dateTime = new DateTime(2018, 9, 10, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 1));

        dateTime = new DateTime(2018, 10, 3, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 0));
        dateTime = new DateTime(2018, 10, 7, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 2));
        dateTime = new DateTime(2018, 10, 8, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 3));
        dateTime = new DateTime(2018, 10, 10, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 4));


        dateTime = new DateTime(2018, 11, 22, 0, 0);
        dateList.add(new ScheduleDate(dateTime, true, 0));
        calendarView.setCalendarData(dateList);
        calendarView.setItemClickListener(new ItemClickListener() {
            @Override public void onItemClick(ScheduleDate scheduleDate) {
                if (scheduleDate.isSelected && scheduleDate.todoCount > 0) {
                    Toast.makeText(MainActivity.this, "有课", Toast.LENGTH_SHORT).show();
                } else if (scheduleDate.isSelected && scheduleDate.todoCount == 0) {
                    Toast.makeText(MainActivity.this, "排课中", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "没有课", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
