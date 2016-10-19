package demo.lieeber.com.calendar;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by lieeber on 16/9/8.
 */
public class CalendarViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList monthList = new ArrayList<>();
    private CellClickListener cellClickListener;

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_view, parent, false);
            return new CellViewHolder(viewHolder);
        } else {
            return new MonthTitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.month_title, parent, false));
        }
    }


    public  interface  CellClickListener{
        void onCellClick(ScheduleDate scheduleDate);
    }

    public void  setOnCellClickListener(CellClickListener listener) {
        this.cellClickListener = listener;
    }
    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Object itemData = monthList.get(position);
        if (itemData instanceof ScheduleDate) {
            final ScheduleDate scheduleDate = (ScheduleDate) itemData;
            CellViewHolder myViewHolder = (CellViewHolder) holder;
            myViewHolder.setData(scheduleDate);
            myViewHolder.itemView.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    if (cellClickListener != null) {
                        cellClickListener.onCellClick(scheduleDate);
                    }
                }
            });
        } else {
            MonthTitleHolder monthTitleHolder = (MonthTitleHolder) holder;
            MonthTitleModel monthModel = (MonthTitleModel) itemData;
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
        if (monthList.get(position) instanceof ScheduleDate) {
            return 0;
        } else {
            return 1;

        }
    }

    public Object getItem(int position) {
        return monthList.get(position);
    }
}
