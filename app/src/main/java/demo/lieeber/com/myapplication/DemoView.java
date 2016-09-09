package demo.lieeber.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lieeber on 16/9/2.
 */

public class DemoView extends View {


    private Paint mPaint;


    // 1.创建Picture
    private Picture mPicture = new Picture();

    public DemoView(Context context) {
        super(context);

    }

    public DemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Style.STROKE);
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        recording();
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        // 将坐标系原点移动到画布正中心
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        mPicture.draw(canvas);
//        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),mPicture.getHeight()));

//        canvas.drawPicture(mPicture);

//        PictureDrawable drawable = new PictureDrawable(mPicture);
//// 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
//        drawable.setBounds(0, 0, this.getWidth(), this.getHeight());
//// 绘制
//        drawable.draw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aa);
        Rect src = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        Rect dst = new Rect(0,0,this.getWidth(),this.getHeight());

        canvas.drawBitmap(bitmap, src, dst,null);


    }

    // 2.录制内容方法
    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        // 将坐标系原点移动到画布正中心
        canvas.translate(getWidth() / 2, getHeight() / 2);

        RectF rect = new RectF(-400, -400, 400, 400);   // 矩形区域

        for (int i = 0; i <= 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rect, mPaint);
        }

        mPicture.endRecording();
    }

}
