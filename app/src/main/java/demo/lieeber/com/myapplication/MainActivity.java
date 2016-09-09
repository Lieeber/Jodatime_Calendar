package demo.lieeber.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,7);
//        gridLayoutManager.setSpanCount(7);

        recyclerView.setLayoutManager(linearLayoutManager);
        final MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
//        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
//            @Override public int getSpanSize(int position) {
//                mAdapter.getItem();
//                return 0;
//            }
//        });
        DateTime dateTime = new DateTime();
        DateTime startDate = dateTime.minusMonths(8);
        while (startDate.getDayOfMonth() != 1) {
            startDate = startDate.minusDays(1);
        }
        ArrayList monthList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            monthList.add(new MonthModel(startDate.monthOfYear().getAsShortText()));
            ArrayList<DateTime> dateTimes = new ArrayList<>();
            int dayOfWeek = startDate.getDayOfWeek();
            for (int j = 1; j < dayOfWeek; j++) {
                dateTimes.add(new DateTime(2000, 1, 1, 1, 1));
            }
            for (int j = 0; j < 31; j++) {
                dateTimes.add(startDate);
                DateTime tempDate = startDate.plusDays(1);
                if (startDate.getMonthOfYear() != tempDate.getMonthOfYear()) {
                    monthList.add(dateTimes);
                    startDate = tempDate;
                    break;
                } else {
                    startDate = tempDate;
                }
            }
        }
        for (int i = 0; i < monthList.size(); i++) {
            if (monthList.get(i) instanceof ArrayList) {
                ArrayList<DateTime> item = (ArrayList<DateTime>) monthList.get(i);
                Log.e("--------------------------------------------", item.get(0).getMonthOfYear() + "");
                for (int j = 0; j < item.size(); j++) {
                    Log.e("============", item.get(j).getDayOfMonth() + "");

                }
            }
        }
        mAdapter.setData(monthList);

//        DateTime dateTime = new DateTime();
//        dateTime = dateTime.withMonthOfYear(12);
//        int dayOfMonth = dateTime.getDayOfMonth();
//
////        Log.e("=========dayOfMonth", dayOfMonth + "");
////        String asText = dateTime.monthOfYear().getAsText();
////        Log.e("=========asText", asText + "");
////        int dayOfMonth1 = dateTime.dayOfMonth().getMaximumValue();
////
////        Log.e("=========dayOfMonth1", dayOfMonth1 + "");
////        int dayOfWeek = dateTime.getDayOfWeek();
////        Log.e("=========dayOfWeek", dayOfWeek + "");
//        dateTime = dateTime.plusMonths(1);
//        Log.e("=========dayOfWeek", dateTime.getMonthOfYear() + "");


    }
}
