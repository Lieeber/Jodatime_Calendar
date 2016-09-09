package demo.lieeber.com.myapplication;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lieeber on 16/9/8.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList monthList = new ArrayList<>();

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.monthview, parent, false);
            return new MyViewHolder(viewHolder);
        } else {
            return new MonthTitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.month_title, parent, false));
        }
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Object itemData = monthList.get(position);
        if (itemData instanceof List) {
            List<DateTime> dateTimes = (List<DateTime>) itemData;
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.setData(dateTimes);
        } else {
            MonthTitleHolder monthTitleHolder = (MonthTitleHolder) holder;
            MonthModel monthModel = (MonthModel) itemData;
            monthTitleHolder.setData(monthModel);

        }

    }

    @Override public int getItemCount() {
        Log.e("===========", monthList.size() + "");
        return monthList.size();
    }

    //
    public void setData(ArrayList monthList) {
        this.monthList = monthList;
        notifyDataSetChanged();
    }

    @Override public int getItemViewType(int position) {
        if (monthList.get(position) instanceof List) {
            return 0;
        } else {
            return 1;

        }
    }

    public Object getItem(int position) {
      return   monthList.get(position);
    }
}
