package demo.lieeber.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lieeber on 16/9/8.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    private final MyGridAdapter myGridAdapter;
    private final Context context;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        GridView mGridView = (GridView) itemView.findViewById(R.id.grid_view);
        myGridAdapter = new MyGridAdapter();
        mGridView.setAdapter(myGridAdapter);
    }

    public void setData(List<DateTime> dateTimes) {
        myGridAdapter.setData(dateTimes);
    }


    private class MyGridAdapter extends BaseAdapter {
        private List<DateTime> dateTimes = new ArrayList<>();
        @Override public int getCount() {
            return dateTimes.size();
        }

        @Override public Object getItem(int position) {
            return null;
        }

        @Override public long getItemId(int position) {
            return 0;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new CellView(context);
            }
            ((CellView) convertView).setData(dateTimes.get(position));
            return convertView;
        }


        public void setData(List<DateTime> dateTimes) {
            this.dateTimes = dateTimes;
            notifyDataSetChanged();
        }
    }
}
