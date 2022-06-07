package com.example.androiddrawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Drawing extends View {
    private Paint brush;
    private Paint redBrush;
    private LinearGradient linearGradient;
    private SweepGradient sweepGradient;
    private RadialGradient radialGradient;
    private Bitmap bitmap;

    public Drawing(Context context) {
        super(context);
        init();
    }

//    public Drawing(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public Drawing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public Drawing(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    public void init(){
        bitmap =  BitmapFactory.decodeResource(getResources(),R.drawable.cam);
        //initatiate gradient
        radialGradient = new RadialGradient(0,0,20,
                Color.GREEN,Color.BLUE,Shader.TileMode.REPEAT);//shadow,MIRROR,REPEAT,CLAMP
        linearGradient = new LinearGradient(0,0,200, 200,
                Color.RED,Color.BLACK, Shader.TileMode.REPEAT);//SHADOW
        brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        redBrush = new Paint(Paint.ANTI_ALIAS_FLAG);
        redBrush.setStrokeWidth(23f);
        brush.setColor(Color.parseColor("green"));
        redBrush.setColor(Color.RED);
//        brush.setShader(linearGradient);
        brush.setShader(radialGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, (getMeasuredWidth()/2)-(bitmap.getWidth()/2),
                (getMeasuredHeight()/2)-(bitmap.getHeight()/2),null);
//        super.onDraw(canvas);
//        canvas.drawCircle(getMeasuredWidth()/2,
//                getMeasuredHeight()/2,308f,brush);
//        canvas.drawLine(0,0, getMeasuredWidth()/2,
//                getMeasuredHeight()/2, redBrush);
        canvas.save();
        super.onDraw(canvas);
    }
}
