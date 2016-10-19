package demo.lieeber.com.calendar;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by lieeber on 16/9/8.
 */
public class CellViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final TextView tvDay;
    private final TextView tvStatus;

    public CellViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        tvDay = (TextView) itemView.findViewById(R.id.tv_day);
        tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
    }

    public void setData(ScheduleDate scheduleDate) {
        if (scheduleDate.isSpace) {
            itemView.setBackgroundColor(Color.parseColor("#00000000"));
            tvStatus.setText("");
            tvDay.setText("");
        } else {
            int dayOfMonth = scheduleDate.dateTime.getDayOfMonth();
            tvDay.setText(dayOfMonth + "");
            boolean isSelected = scheduleDate.isSelected;
            int todoCount = scheduleDate.todoCount;
            boolean isFinished = scheduleDate.isFinished;
            if (isSelected) {
                if (isFinished) {
                    itemView.setBackgroundColor(Color.parseColor("#eeeeee"));
                    tvStatus.setText("已完成");
                } else {
                    if (todoCount == 0) {
                        itemView.setBackgroundColor(Color.parseColor("#aaaaaa"));
                        tvStatus.setText("排课中");
                    } else {
                        itemView.setBackgroundColor(Color.parseColor("#876598"));
                        tvStatus.setText("有课");
                    }
                }
            } else {
                if (scheduleDate.isBeforeNow) {
                    itemView.setBackgroundColor(Color.parseColor("#f4f4f4"));
                    tvStatus.setText("");
                } else {
                    itemView.setBackgroundColor(Color.parseColor("#00000000"));
                    tvStatus.setText("");
                }
            }
        }
    }

}
