package com.kevin.library.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
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

public class StepView extends View {

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
  private Paint mLinePaint;
  private int mPosition;
  private float mRadius;
  private float mLineLenth;
  private int mWidth;
  private int mHeight;
  private int[] mInit;
  private int mStepPosition;
  private float mTextSize;
  private GestureDetectorCompat mDetector;

  public StepView(Context context) {
    super(context);
    init(context, null);
  }

  public StepView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context,attrs);
  }

  public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs);
  }

  public void init(Context context, AttributeSet attrs) {
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StepView);
    mViewCount = array.getInteger(R.styleable.StepView_step_count, 3);
    mBackGroundSelected = array.getColor(R.styleable.StepView_background_selected, Color.RED);
    mBackGroundUnselected = array.getColor(R.styleable.StepView_background_unselected, Color.BLACK);
    mColorSelected = array.getColor(R.styleable.StepView_color_selected, Color.WHITE);
    mColorUnselected = array.getColor(R.styleable.StepView_color_unselected, Color.RED);
    mLineHeight = array.getDimension(R.styleable.StepView_line_height, 20);
    mRadius = array.getDimension(R.styleable.StepView_circle_radius, 5);
    mStepPosition = array.getInteger(R.styleable.StepView_step_position, 0);
    mTextSize = array.getDimension(R.styleable.StepView_text_size, 16);
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
    mTextPaint.setStyle(Paint.Style.FILL);
    mTextPaint.setTextSize(mTextSize);
    mLinePaint = new Paint();
    mLinePaint.setColor(mBackGroundSelected);
    mLinePaint.setAntiAlias(true);
    mLinePaint.setStrokeWidth(10);
    mLinePaint.setStyle(Paint.Style.FILL);
    //mDetector = new GestureDetectorCompat(getContext(), this);
    //mDetector.setOnDoubleTapListener(this);
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
    for (int i = 0; i <= mViewCount && mPosition <= mViewCount; i++) {
      //如果小于position
      float textWidth = mTextPaint.measureText(mPosition + "");
      Rect rect = new Rect();
      mTextPaint.getTextBounds(mPosition + "", 0, (mPosition + "").length(), rect);
      if (i <= mStepPosition) {
        mCirclePaint.setColor(mBackGroundSelected);
        mLinePaint.setColor(mBackGroundSelected);
        //获取view的当前圆心点。
        mLineLenth = computeLineLength(mWidth);
        if (mStepPosition == 0) {
          canvas.drawCircle(mInit[0] + mRadius, mInit[1], mRadius, mCirclePaint);
          Log.d(TAG, "canvasCountType: " + rect.top);
          Log.d(TAG, "canvasCountType: " + rect.bottom);
          canvas.drawText(mPosition + "", mInit[0] + mRadius - (textWidth / 2),
              mInit[1] + (rect.height() / 2), mTextPaint);
          mLinePaint.setColor(mBackGroundUnselected);
          canvas.drawLine(mInit[0] + (2 * mRadius), mInit[1],
              mInit[0] + (2 * mRadius) + computeLineLength(mWidth), mInit[1], mLinePaint);
        } else {
          canvas.drawCircle(mInit[0] + mRadius + mPosition * (2 * mRadius + mLineLenth), mInit[1],
              mRadius, mCirclePaint);
          canvas.drawText(mPosition + "",
              mInit[0] + mRadius + mPosition * (2 * mRadius + mLineLenth) - (textWidth / 2),
              mInit[1] + (rect.height() / 2), mTextPaint);
          canvas.drawLine(mInit[0] + (2 * mRadius) + (mPosition - 1) * (2 * mRadius + mLineLenth),
              mInit[1],
              mInit[0] + (2 * mRadius) + (mPosition - 1) * (2 * mRadius + mLineLenth) + mLineLenth,
              mInit[1], mLinePaint);
        }
        mPosition++;
      } else {
        mCirclePaint.setColor(mBackGroundUnselected);
        mLinePaint.setColor(mBackGroundUnselected);
        canvas.drawCircle(mInit[0] + mRadius + mPosition * (2 * mRadius + mLineLenth), mInit[1],
            mRadius, mCirclePaint);
        canvas.drawText(mPosition + "",
            mInit[0] + mRadius + mPosition * (2 * mRadius + mLineLenth) - (textWidth / 2),
            mInit[1] + (rect.height() / 2), mTextPaint);
        canvas.drawLine(mInit[0] + (2 * mRadius) + (mPosition - 1) * (2 * mRadius + mLineLenth),
            mInit[1],
            mInit[0] + (2 * mRadius) + (mPosition - 1) * (2 * mRadius + mLineLenth) + mLineLenth,
            mInit[1], mLinePaint);
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

  @Override public boolean onTouchEvent(MotionEvent event) {
    //首先，我们需要将本身空间扩大到当前activity大小，使点击效果更大。
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        Log.d(TAG, "onTouchEvent: 你按下了手指");
        break;
      case MotionEvent.ACTION_MOVE:
        Log.d(TAG, "onTouchEvent: 你移动了手指");
        break;
      case MotionEvent.ACTION_HOVER_MOVE:
        break;
      case MotionEvent.ACTION_BUTTON_PRESS:
        break;
      case MotionEvent.ACTION_BUTTON_RELEASE:
        break;
      case MotionEvent.ACTION_CANCEL:
        break;
      case MotionEvent.ACTION_HOVER_ENTER:
        break;
      case MotionEvent.ACTION_HOVER_EXIT:
        break;
      case MotionEvent.ACTION_MASK:
        break;
      case MotionEvent.ACTION_OUTSIDE:
        break;
      case MotionEvent.ACTION_POINTER_DOWN:
        break;
      case MotionEvent.ACTION_POINTER_INDEX_MASK:
        break;
      case MotionEvent.ACTION_SCROLL:
        break;
      case MotionEvent.ACTION_POINTER_UP:
        break;
      case MotionEvent.ACTION_UP:
        break;
    }
    return super.onTouchEvent(event);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
  }
}
