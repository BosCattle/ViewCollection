package com.kevin.library.widget.heartview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.kevin.library.R;
import com.kevin.library.util.ScreenUtils;

/**
 * Class: DashedLine </br>
 * Description: 虚线 </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 9/30/16 3:59 PM</br>
 * Update: 9/30/16 3:59 PM </br>
 **/

public class DashedLine extends AppCompatTextView {

  private int mLinePadding = 0;
  private int mLineColor;

  public DashedLine(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DashedLine);
    mLinePadding = (int) array.getDimension(R.styleable.DashedLine_inner_padding,
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
            getResources().getDisplayMetrics()));
    mLineColor =
        array.getColor(R.styleable.DashedLine_line_color, getTextColors().getDefaultColor());
  }
  @SuppressLint("DrawAllocation")
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();
    paint.setStyle(Paint.Style.STROKE);//设置画笔style空心
    paint.setColor(mLineColor);
    paint.setStrokeWidth(2);//设置画笔宽度
    //我自己的
    TextPaint textPaint = new TextPaint();
    float textWidth = ScreenUtils.dp2px(getContext(),textPaint.measureText(getText().toString()));
    float lineWidth = (getWidth()-getPaddingRight()-getPaddingLeft()-textWidth-(2*mLinePadding))/2;
    Path path = new Path();
    path.moveTo(getPaddingLeft(), getHeight() / 2);
    path.lineTo(getPaddingLeft()+lineWidth, getHeight() / 2);
    PathEffect effect = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
    paint.setPathEffect(effect);
    canvas.drawPath(path, paint);
    path.moveTo(lineWidth+getPaddingLeft()+(2*mLinePadding)+textWidth, getHeight() / 2);
    path.lineTo((getWidth()-getPaddingRight()), getHeight() / 2);
    PathEffect effect1 = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
    paint.setPathEffect(effect1);
    canvas.drawPath(path, paint);
  }
}
