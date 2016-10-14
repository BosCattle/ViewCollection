package com.kevin.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.kevin.library.R;
import com.kevin.library.util.ScreenUtils;

/**
 * Class: StepView </br>
 * Description: 步骤指示器 </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 10/2/16 8:28 PM</br>
 * Update: 10/2/16 8:28 PM </br>
 **/

public class StepView extends View implements View.OnTouchListener {

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
  private TextPaint mTextPaint;
  private int mPosition;
  private float mRadius;
  private float mLineLenth;
  private int mWidth;
  private int mHeight;
  private int[] mInit;

  public StepView(Context context) {
    super(context, null);
    init(context, null);
  }

  public StepView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  public void init(Context context, AttributeSet attrs) {
    this.setOnTouchListener(this);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StepView);
    mViewCount = array.getInteger(R.styleable.StepView_step_count, 3);
    mBackGroundSelected = array.getColor(R.styleable.StepView_background_selected, Color.RED);
    mBackGroundUnselected = array.getColor(R.styleable.StepView_background_unselected, Color.BLACK);
    mColorSelected = array.getColor(R.styleable.StepView_color_selected, Color.WHITE);
    mColorUnselected = array.getColor(R.styleable.StepView_color_unselected, Color.RED);
    mLineHeight = array.getDimension(R.styleable.StepView_line_height, 20);
    mRadius = array.getDimension(R.styleable.StepView_circle_radius, 5);
    array.recycle();
    mCirclePaint = new Paint();
    mCirclePaint.setColor(mBackGroundSelected);
    mCirclePaint.setAntiAlias(true);
    mCirclePaint.setStrokeWidth(2);
    mCirclePaint.setStyle(Paint.Style.FILL);
    mTextPaint = new TextPaint();
    mTextPaint.setColor(mColorSelected);
    mTextPaint.setAntiAlias(true);
    mTextPaint.setStrokeWidth(2);
    mTextPaint.setStyle(Paint.Style.STROKE);
    mTextPaint.setTextSize(50);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    mInit = new int[2];
    mInit[0] = l;
    mInit[1] = t + (b - t) / 2;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    Log.d(TAG, "onMeasure: " + MeasureSpec.getSize(heightMeasureSpec));
    int xpad = (getPaddingLeft() + getPaddingRight());
    int ypad = (getPaddingTop() + getPaddingBottom());
    mWidth = MeasureSpec.getSize(widthMeasureSpec) - xpad;
    mHeight = MeasureSpec.getSize(heightMeasureSpec) - ypad;
    int mWidthSpec = MeasureSpec.getMode(widthMeasureSpec);
    int mHeightSpec = MeasureSpec.getMode(heightMeasureSpec);
    setMeasuredDimension(mWidth, ScreenUtils.dp2px(getContext(), 100));
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvasCountType(canvas);
  }

  private int measureWidth(int measureSpec) {
    return 0;
  }

  public int measureHeight(int measureSpec) {
    return 0;
  }

  public void canvasCountType(Canvas canvas) {
    if (mViewCount <= 0) {
      throw new RuntimeException("count can not be zero or minus.");
    }
    if (mRadius <= 0) {
      throw new RuntimeException("radius can not be zero or minus.");
    }
    for (int i = 0; i <= mViewCount; i++) {
      //如果小于position
      if (i <= mPosition) {
        //获取view的当前圆心点。
        mLineLenth = computeLineLength(mWidth);
        if (mPosition == 0) {
          canvas.drawCircle(mInit[0] + mRadius, mInit[1], mRadius, mCirclePaint);
          canvas.drawText(mPosition + "", mInit[0] + mRadius, mInit[1], mTextPaint);
          mPosition++;
        } else {
          canvas.drawCircle(mInit[0] + mRadius + mPosition * (2 * mRadius + mLineLenth), mInit[1],
              mRadius, mCirclePaint);
          canvas.drawText(mPosition + "", mInit[0] + mRadius + mPosition * (2 * mRadius + mLineLenth),
              mInit[1], mTextPaint);
          mPosition++;
        }
      } else {
        canvas.drawCircle(mInit[0] + mRadius + mPosition * (2 * mRadius + mLineLenth), mInit[1],
            mRadius, mCirclePaint);
        canvas.drawText(mPosition + "", mInit[0] + mRadius + mPosition * (2 * mRadius + mLineLenth),
            mInit[1], mTextPaint);
        mPosition++;
      }
    }
  }

  /**
   * 获取线的长度
   */
  public float computeLineLength(float width) {
    return (width - (2 * mRadius * mViewCount)) / (mViewCount - 1);
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
