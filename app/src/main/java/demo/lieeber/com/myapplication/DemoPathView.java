package demo.lieeber.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lieeber on 16/9/2.
 */

public class DemoPathView extends View {

    private final Paint mPaint;

    public DemoPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(5);
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);  // 移动坐标系到屏幕中心
        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        Path src = new Path();

        path.addRect(-200,-200,200,200, Path.Direction.CW);
        src.addCircle(0,0,100, Path.Direction.CW);

        path.addPath(src,0,0);

        mPaint.setColor(Color.BLACK);           // 绘制合并后的路径
        canvas.drawPath(path,mPaint);
    }
}
