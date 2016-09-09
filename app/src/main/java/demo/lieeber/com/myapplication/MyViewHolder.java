package demo.lieeber.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

/**
 * Created by lieeber on 16/9/8.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final TextView tvDay;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        tvDay = (TextView) itemView.findViewById(R.id.tv_day);
    }

    public void setData(DateTime dateTime) {
        if (dateTime.getYear() == 2000 && dateTime.getMonthOfYear() == 1) {
            tvDay.setText("");
        } else {
            int dayOfMonth = dateTime.getDayOfMonth();
            tvDay.setText(dayOfMonth + "");
        }
    }

}
