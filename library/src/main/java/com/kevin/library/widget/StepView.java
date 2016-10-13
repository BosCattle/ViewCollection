package com.kevin.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.kevin.library.R;

/**
 * Class: StepView </br>
 * Description: 步骤指示器 </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 10/2/16 8:28 PM</br>
 * Update: 10/2/16 8:28 PM </br>
 **/

public class StepView extends View implements View.OnTouchListener  {

  private static final String TAG = StepView.class.getSimpleName();
  private float mOldXValue;
  private float mMoveSize = 20;
  private int mViewCount = 3;
  private @ColorInt int mBackGroundSelected;
  private @ColorInt int mBackGroundUnselected;
  private @ColorInt int mColorSelected;
  private @ColorInt int mColorUnselected;
  private float mLineHeight;
  private Paint mCirclePaint;
  private Paint mTextPaint;
  private int mPosition;
  private int mRadius;
  private float mLineLenth;

  public StepView(Context context) {
    super(context,null);
    init(context,null);
  }

  public StepView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context,attrs);
  }

  public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs);
  }

  public void init(Context context,AttributeSet attrs){
    this.setOnTouchListener(this);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StepView);
    mViewCount = array.getInteger(R.styleable.StepView_step_count,3);
    mBackGroundSelected = array.getColor(R.styleable.StepView_background_selected, Color.RED);
    mBackGroundUnselected = array.getColor(R.styleable.StepView_background_unselected,Color.BLACK);
    mColorSelected = array.getColor(R.styleable.StepView_color_selected,Color.WHITE);
    mColorUnselected = array.getColor(R.styleable.StepView_color_unselected,Color.RED);
    mLineHeight = array.getDimension(R.styleable.StepView_line_height,20);
    mRadius = array.getInteger(R.styleable.StepView_circle_radius,5);
    array.recycle();
    mCirclePaint = new Paint();
    mCirclePaint.setColor(mBackGroundSelected);
    mCirclePaint.setAntiAlias(true);
    mCirclePaint.setStrokeWidth(2);
    mCirclePaint.setStyle(Paint.Style.STROKE);
    mTextPaint = new Paint();
    mTextPaint.setColor(mColorSelected);
    mCirclePaint.setAntiAlias(true);
    mCirclePaint.setStrokeWidth(2);
    mCirclePaint.setStyle(Paint.Style.STROKE);
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvasCountType(canvas);
  }


  public void canvasCountType(Canvas canvas){
    if (mViewCount<=0){
      throw new RuntimeException("count can not be zero or minus.");
    }
    if (mRadius<=0){
      throw new RuntimeException("radius can not be zero or minus.");
    }
    for (int i = 0;i<=mViewCount;i++){
      //如果小于position
      if (i<=mPosition){
        //获取view的当前圆心点。
        float left = getLeft();
        float right = getRight();
        float allWidth = right-left;
        mLineLenth = computeLineLength(allWidth);
        if (mPosition==0) {
          canvas.drawCircle(getX()+mRadius, getY(), mRadius, mCirclePaint);
          canvas.drawText(mPosition+"",getX()+mRadius, getY(),mTextPaint);
          mPosition++;
        }else {
          canvas.drawCircle(getX()+mRadius+mPosition*(2*mRadius+mLineLenth),getY(),mRadius,mCirclePaint);
          canvas.drawText(mPosition+"",getX()+mRadius+mPosition*(2*mRadius+mLineLenth),getY(),mTextPaint);
          mPosition++;
        }
      }else {
        canvas.drawCircle(getX()+mRadius+mPosition*(2*mRadius+mLineLenth),getY(),mRadius,mCirclePaint);
        canvas.drawText(mPosition+"",getX()+mRadius+mPosition*(2*mRadius+mLineLenth),getY(),mTextPaint);
        mPosition++;
      }
    }
  }

  /**
   * 获取线的长度
   * @param width
   * @return
   */
  public float computeLineLength(float width){
    return (width-(2*mRadius*mViewCount))/(mViewCount-1);
  }

  @Override public boolean onTouch(View v, MotionEvent event) {
    //绘制几个不同的视图，根据传入的count，设定前面绘制view的流程.

    return true;
  }

  public float getmLineHeight() {
    return mLineHeight;
  }

  public void setmLineHeight(int mLineHeight) {
    this.mLineHeight = mLineHeight;
  }

  public int getmColorUnselected() {
    return mColorUnselected;
  }

  public void setmColorUnselected(int mColorUnselected) {
    this.mColorUnselected = mColorUnselected;
  }

  public int getmColorSelected() {
    return mColorSelected;
  }

  public void setmColorSelected(int mColorSelected) {
    this.mColorSelected = mColorSelected;
  }

  public int getmBackGroundUnselected() {
    return mBackGroundUnselected;
  }

  public void setmBackGroundUnselected(int mBackGroundUnselected) {
    this.mBackGroundUnselected = mBackGroundUnselected;
  }

  public int getmBackGroundSelected() {
    return mBackGroundSelected;
  }

  public void setmBackGroundSelected(int mBackGroundSelected) {
    this.mBackGroundSelected = mBackGroundSelected;
  }

  public int getmViewCount() {
    return mViewCount;
  }

  public void setmViewCount(int mViewCount) {
    this.mViewCount = mViewCount;
  }
}
