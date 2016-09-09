package demo.lieeber.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lieeber on 16/9/2.
 */

public class DemoTextView extends View {

    private Paint textPaint;

    public DemoTextView(Context context) {
        super(context);
    }

    public DemoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 创建画笔
        textPaint = new Paint();
        textPaint.setColor(Color.BLUE);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(50);              // 设置字体大小
    }

    @Override protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        // 文本(要绘制的内容)
//        String str = "ABCDEFG";
//
//// 参数分别为 (文本 基线x 基线y 画笔)
//        canvas.drawText(str,200,500,textPaint);

        String str = "SLOOP";

        canvas.drawPosText(str,new float[]{
                100,100,    // 第一个字符位置
                200,200,    // 第二个字符位置
                300,300,    // ...
                400,400,
                500,500
        },textPaint);
    }
}
