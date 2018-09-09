package com.zhengyi.customview.drawer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class BlockView extends AppCompatImageView {
    private Region mRegion;
    private Path mPath;
    private Paint mPaint;
    private int mWidth, mHeight;
    public BlockView(Context context) {
        this(context, null);
    }

    public BlockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mRegion = new Region();
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (mRegion.contains((int)event.getX(),(int)event.getY())){
                    Log.e("event ------------->","在区域内");
                }else {
                    Log.e("event ------------->","不在区域内");
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPath.moveTo((float) (mHeight /Math.tan(Math.PI/180*60)),0);
        mPath.lineTo(0,mHeight);
        mPath.lineTo(mWidth - (float) (mHeight /Math.tan(Math.PI/180*60)),mHeight);
        mPath.lineTo(mWidth, 0);
        mPath.close();
//        mPath.moveTo(0,mHeight);
//        mPath.lineTo(mWidth - 30,mHeight);
//        mPath.lineTo(mWidth,0);
//        mPath.lineTo(30, 0);
//        mPath.close();
        mRegion.setPath(mPath, new Region(0,0,mWidth, mHeight));
        canvas.drawPath(mPath,mPaint);
        Drawable drawable = getDrawable();
        if (drawable !=null){
            Rect rect = drawable.getBounds();
            Log.e("----------------->","left:"+rect.left+", top:"+rect.top+", " +
                    "right:"+rect.right+", bottom:"+rect.bottom);
        }
        super.onDraw(canvas);
    }
}
