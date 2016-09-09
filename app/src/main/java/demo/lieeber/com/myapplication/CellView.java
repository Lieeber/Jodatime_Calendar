package demo.lieeber.com.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.joda.time.DateTime;

/**
 * Created by lieeber on 16/9/9.
 */
public class CellView extends FrameLayout {

    private TextView tvDay;

    public CellView(Context context) {
        super(context);
        initView();
    }



    public CellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    private void initView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.cell_view, this, true);
        tvDay = (TextView) contentView.findViewById(R.id.tv_day);
    }

    public void setData(DateTime dateTime) {
        if (dateTime.getYear() == 2000 && dateTime.getMonthOfYear() == 1) {
            tvDay.setText("");
        } else {
            int dayOfMonth = dateTime.getDayOfMonth();
            tvDay.setText(dayOfMonth+"");
        }

    }
}
