package com.kevin.android.ui;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;

import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import com.bumptech.glide.Glide;
import com.kevin.android.R;
import com.kevin.library.widget.heartview.HeartView;
import com.kevin.library.widget.heartview.HeartViewCallBack;
import de.hdodenhof.circleimageview.CircleImageView;

public class HeartViewActivity extends AppCompatActivity implements HeartViewCallBack {

  private HeartView mHeartView;
  private CircleImageView mLeftCircleImageView;
  private CircleImageView mRightCircleImageView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_heart_view);
    mHeartView = (HeartView) findViewById(R.id.heart_view);
    mHeartView.setHeartViewCallBack(this);
    mLeftCircleImageView = (CircleImageView) findViewById(R.id.profile_image_left);
    mRightCircleImageView = (CircleImageView) findViewById(R.id.profile_image_right);
    Glide.with(this).load(R.mipmap.ic_dialog_positive).centerCrop().into(mLeftCircleImageView);
    Glide.with(this).load(R.mipmap.ic_dialog_info).centerCrop().into(mRightCircleImageView);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    mHeartView.reDraw();
    return super.onTouchEvent(event);
  }

  public static void startHeartView(Activity activity) {
    Intent intent = new Intent(activity, HeartViewActivity.class);
    activity.startActivity(intent);
  }

  @Override public void onHeartStart() {

  }

  @Override public void onHeartFinish() {
    runOnUiThread(new Runnable() {
      @Override public void run() {
        WindowManager manager = getWindow().getWindowManager();
        Display display = manager.getDefaultDisplay();
        mLeftCircleImageView.setVisibility(View.VISIBLE);
        mRightCircleImageView.setVisibility(View.VISIBLE);
        int width = display.getWidth();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLeftCircleImageView, "translationX", 0f,
            width / 2-200);
        ObjectAnimator animator1 =
            ObjectAnimator.ofFloat(mRightCircleImageView, "translationX",width ,
                width / 3);
        animator.setDuration(2000).setInterpolator(new BounceInterpolator());
        animator1.setDuration(2000).setInterpolator(new BounceInterpolator());
        animator.start();
        animator1.start();
      }
    });
  }

  public static int px2dip(Context context,float pxValue) {
    float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue/scale+0.5f);
  }
}
