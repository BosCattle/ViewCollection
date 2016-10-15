package com.kevin.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
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
  private VelocityTracker mVelocityTracker;
  private int mTouchSlop;
  private boolean mIsScroll ;
  private boolean mDirect;

  public StepView(Context context) {
    super(context);
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
    ViewConfiguration vc = ViewConfiguration.get(getContext());
    mTouchSlop = vc.getScaledTouchSlop();
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
    for (mPosition = 0; mPosition <= mViewCount; mPosition++) {
      //如果小于position
      float textWidth = mTextPaint.measureText(mPosition + "");
      Rect rect = new Rect();
      mTextPaint.getTextBounds(mPosition + "", 0, (mPosition + "").length(), rect);
      if (mPosition <= mStepPosition) {
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
      }
    }
    mPosition = mStepPosition;
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
    int index = event.getActionIndex();
    int action = event.getActionMasked();
    int pointerId = event.getPointerId(index);
    float lastX = 0;
    float lastY = 0;
    float currentX = 0;
    float currentY = 0;
    switch (action) {
      //手指按下
      case MotionEvent.ACTION_DOWN:
        Log.d(TAG, "onTouchEvent: ACTION_DOWN");
        if (mVelocityTracker == null) {
          mVelocityTracker = VelocityTracker.obtain();
        } else {
          mVelocityTracker.clear();
        }
        mVelocityTracker.addMovement(event);
        lastX = event.getX();
        lastY = event.getY();
        mIsScroll = false;
        break;
      //手指移动，注意，移动期间会多次收集到这个信息
      case MotionEvent.ACTION_MOVE:
        Log.d(TAG, "onTouchEvent: ACTION_MOVE");
        mVelocityTracker.addMovement(event);
        mVelocityTracker.computeCurrentVelocity(1000);
        currentX = event.getX();

        if (VelocityTrackerCompat.getXVelocity(mVelocityTracker, pointerId) > 1000
            && mStepPosition < mViewCount
            && Math.abs(currentX - lastX) >= mTouchSlop) {
          mIsScroll = true;
          mDirect = true;
        }
        if (VelocityTrackerCompat.getXVelocity(mVelocityTracker, pointerId) < -1000
            && mStepPosition > 0
            && Math.abs(currentX - lastX) >= mTouchSlop) {
          mIsScroll = true;
          mDirect = false;
        }
        break;
      //第二根手指，或者背的手指按下
      case MotionEvent.ACTION_POINTER_DOWN:
        Log.d(TAG, "onTouchEvent: ACTION_POINTER_DOWN");
        break;
      // 滚动事件
      case MotionEvent.ACTION_SCROLL:
        Log.d(TAG, "onTouchEvent: ACTION_SCROLL");
        break;
      // 第二根手指或者别的手指抬起
      case MotionEvent.ACTION_POINTER_UP:
        Log.d(TAG, "onTouchEvent: ACTION_POINTER_UP");
        break;
      // 最后一根手指抬起
      case MotionEvent.ACTION_UP:
        if (mIsScroll){
          if (mDirect){
            nextStep();
          }else {
            previous();
          }
        }
        Log.d(TAG, "onTouchEvent: ACTION_UP");
        break;
      //当前手势操作被取消
      case MotionEvent.ACTION_CANCEL:
        mVelocityTracker.recycle();
        Log.d(TAG, "onTouchEvent: ACTION_CANCEL");
        break;
      // 手势操作发生在UI组件外
      case MotionEvent.ACTION_OUTSIDE:
        Log.d(TAG, "onTouchEvent: ACTION_OUTSIDE");
        break;
//------------------------------------------非手势操作，无需关心--------------------------------------//
      //鼠标在view移动
      case MotionEvent.ACTION_HOVER_MOVE:
        Log.d(TAG, "onTouchEvent: ACTION_HOVER_MOVE");
        break;
      // 鼠标按钮
      case MotionEvent.ACTION_BUTTON_PRESS:
        Log.d(TAG, "onTouchEvent: ACTION_BUTTON_PRESS");
        break;
      // 鼠标按钮
      case MotionEvent.ACTION_BUTTON_RELEASE:
        Log.d(TAG, "onTouchEvent: ACTION_BUTTON_RELEASE");
        break;
      // 鼠标进入view
      case MotionEvent.ACTION_HOVER_ENTER:
        Log.d(TAG, "onTouchEvent: ACTION_HOVER_ENTER");
        break;
      //鼠标离开view
      case MotionEvent.ACTION_HOVER_EXIT:
        Log.d(TAG, "onTouchEvent: ACTION_HOVER_EXIT");
        break;
    }
    return super.onTouchEvent(event);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    Log.d(TAG, "onSizeChanged: " + w + " " + h + " " + oldw + " " + oldh);
  }

  public void nextStep() {
    if (mStepPosition >= 0) {
      mStepPosition++;
      ViewCompat.postInvalidateOnAnimation(StepView.this);
    }
  }

  public void previous() {
    if (mStepPosition > 0) {
      mStepPosition--;
      Log.d(TAG, "nextStep: " + mStepPosition);
      ViewCompat.postInvalidateOnAnimation(StepView.this);
    }
  }
}
