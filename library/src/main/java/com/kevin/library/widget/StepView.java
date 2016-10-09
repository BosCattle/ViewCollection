package com.kevin.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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
  // 移动位置大于20才算是移动
  private float mMoveSize = 20;
  public StepView(Context context) {
    super(context);
    this.setOnTouchListener(this);
  }

  public StepView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.setOnTouchListener(this);
  }

  public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

  @Override public boolean onTouch(View v, MotionEvent event) {
    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
        mOldXValue = event.getRawX();
        Log.d(TAG, "dispatchTouchEvent: 你按下了当前点。当前的x点为"+mOldXValue);
        break;
      case MotionEvent.ACTION_MOVE:
        float mCurrentValue = event.getRawX();
        Log.d(TAG, "dispatchTouchEvent: 你移动到了当前点,当前的x点为"+mCurrentValue);
        if (Math.abs(mCurrentValue-mOldXValue)>=mMoveSize){
          if (mCurrentValue>mOldXValue){

          }else {

          }
        }else {
          Log.d(TAG, "dispatchTouchEvent: 你移动的点没有超过20");
        }
        break;
      case MotionEvent.ACTION_UP:
        float upValue = event.getRawX();
        Log.d(TAG, "dispatchTouchEvent: 你的手提起来了。当前的x点为"+upValue);
        break;
    }
    return true;
  }
}
