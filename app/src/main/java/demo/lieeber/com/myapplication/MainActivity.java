package demo.lieeber.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LBCalendarView calendarView = (LBCalendarView) findViewById(R.id.calendar_view);
        calendarView.setYearAndMonth(2014, 1, 2016, 9);
    }
}
