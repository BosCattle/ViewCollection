package com.kevin.library.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Created by kevin on 16-6-10.
 */
public class LayoutHelper {

  public static final int MATCH_PARENT = -1;
  public static final int WRAP_CONTENT = -2;
  public static float density = 1;
  private static Context mContext;

  private LayoutHelper() {
  }

  public static int dp(Context context,float value) {
    if (value == 0) {
      return 0;
    }
    buildDensity(context);
    return (int) Math.ceil(density * value);
  }

  public static float dpf2(Context context,float value) {
    if (value == 0) {
      return 0;
    }
    buildDensity(context);
    return density * value;
  }

  private static float buildDensity(Context context){
    if (context!=null){
      density = context.getApplicationContext().getResources().getDisplayMetrics().density;
      mContext = context;
    }else {
      throw new NullPointerException("context must be not null");
    }
    return density;
  }

  private static int getSize(float size) {
    return (int) (size < 0 ? size : dp(mContext,size));
  }

  public static ViewGroup.LayoutParams createViewGroupLayoutParams() {
    return new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
  }

  public static FrameLayout.LayoutParams createScroll(Context context,int width, int height, int gravity) {
    return new ScrollView.LayoutParams(getSize(width), getSize(height), gravity);
  }

  public static FrameLayout.LayoutParams createFrame(int width, float height, int gravity,
                                                     float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
    FrameLayout.LayoutParams layoutParams =
        new FrameLayout.LayoutParams(getSize(width), getSize(height), gravity);
    layoutParams.setMargins(dp(mContext,leftMargin), dp(mContext,topMargin),
        dp(mContext,rightMargin), dp(mContext,bottomMargin));
    return layoutParams;
  }

  public static FrameLayout.LayoutParams createFrame(int width, int height, int gravity) {
    return new FrameLayout.LayoutParams(getSize(width), getSize(height), gravity);
  }

  public static FrameLayout.LayoutParams createFrame(int width, float height) {
    return new FrameLayout.LayoutParams(getSize(width), getSize(height));
  }

  public static RelativeLayout.LayoutParams createRelative(float width, float height,
                                                           int leftMargin, int topMargin, int rightMargin, int bottomMargin, int alignParent,
                                                           int alignRelative, int anchorRelative) {
    RelativeLayout.LayoutParams layoutParams =
        new RelativeLayout.LayoutParams(getSize(width), getSize(height));
    if (alignParent >= 0) {
      layoutParams.addRule(alignParent);
    }
    if (alignRelative >= 0 && anchorRelative >= 0) {
      layoutParams.addRule(alignRelative, anchorRelative);
    }
    layoutParams.leftMargin = dp(mContext,leftMargin);
    layoutParams.topMargin = dp(mContext,topMargin);
    layoutParams.rightMargin = dp(mContext,rightMargin);
    layoutParams.bottomMargin = dp(mContext,bottomMargin);
    return layoutParams;
  }

  public static RelativeLayout.LayoutParams createRelative(int width, int height, int leftMargin,
                                                           int topMargin, int rightMargin, int bottomMargin) {
    return createRelative(width, height, leftMargin, topMargin, rightMargin, bottomMargin, -1, -1,
        -1);
  }

  public static RelativeLayout.LayoutParams createRelative(int width, int height, int leftMargin,
                                                           int topMargin, int rightMargin, int bottomMargin, int alignParent) {
    return createRelative(width, height, leftMargin, topMargin, rightMargin, bottomMargin,
        alignParent, -1, -1);
  }

  public static RelativeLayout.LayoutParams createRelative(float width, float height,
                                                           int leftMargin, int topMargin, int rightMargin, int bottomMargin, int alignRelative,
                                                           int anchorRelative) {
    return createRelative(width, height, leftMargin, topMargin, rightMargin, bottomMargin, -1,
        alignRelative, anchorRelative);
  }

  public static RelativeLayout.LayoutParams createRelative(int width, int height, int alignParent,
                                                           int alignRelative, int anchorRelative) {
    return createRelative(width, height, 0, 0, 0, 0, alignParent, alignRelative, anchorRelative);
  }

  public static RelativeLayout.LayoutParams createRelative(int width, int height) {
    return createRelative(width, height, 0, 0, 0, 0, -1, -1, -1);
  }

  public static RelativeLayout.LayoutParams createRelative(int width, int height, int alignParent) {
    return createRelative(width, height, 0, 0, 0, 0, alignParent, -1, -1);
  }

  public static RelativeLayout.LayoutParams createRelative(int width, int height, int alignRelative,
                                                           int anchorRelative) {
    return createRelative(width, height, 0, 0, 0, 0, -1, alignRelative, anchorRelative);
  }

  public static LinearLayout.LayoutParams createLinear(int width, int height, float weight,
                                                       int gravity, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
    LinearLayout.LayoutParams layoutParams =
        new LinearLayout.LayoutParams(getSize(width), getSize(height), weight);
    layoutParams.setMargins(dp(mContext,leftMargin), dp(mContext,topMargin),
       dp(mContext,rightMargin), dp(mContext,bottomMargin));
    layoutParams.gravity = gravity;
    return layoutParams;
  }

  public static LinearLayout.LayoutParams createLinear(int width, int height, float weight,
                                                       int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
    LinearLayout.LayoutParams layoutParams =
        new LinearLayout.LayoutParams(getSize(width), getSize(height), weight);
    layoutParams.setMargins(dp(mContext,leftMargin), dp(mContext,topMargin),
        dp(mContext,rightMargin), dp(mContext,bottomMargin));
    return layoutParams;
  }

  public static LinearLayout.LayoutParams createLinear(int width, int height, int gravity,
                                                       int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
    LinearLayout.LayoutParams layoutParams =
        new LinearLayout.LayoutParams(getSize(width), getSize(height));
    layoutParams.setMargins(dp(mContext,leftMargin), dp(mContext,topMargin),
        dp(mContext,rightMargin), dp(mContext,bottomMargin));
    layoutParams.gravity = gravity;
    return layoutParams;
  }

  public static LinearLayout.LayoutParams createLinear(int width, int height, float leftMargin,
                                                       float topMargin, float rightMargin, float bottomMargin) {
    LinearLayout.LayoutParams layoutParams =
        new LinearLayout.LayoutParams(getSize(width), getSize(height));
    layoutParams.setMargins(dp(mContext,leftMargin), dp(mContext,topMargin),
        dp(mContext,rightMargin), dp(mContext,bottomMargin));
    return layoutParams;
  }

  public static LinearLayout.LayoutParams createLinear(int width, int height, float weight,
                                                       int gravity) {
    LinearLayout.LayoutParams layoutParams =
        new LinearLayout.LayoutParams(getSize(width), getSize(height), weight);
    layoutParams.gravity = gravity;
    return layoutParams;
  }

  public static LinearLayout.LayoutParams createLinear(int width, int height, int gravity) {
    LinearLayout.LayoutParams layoutParams =
        new LinearLayout.LayoutParams(getSize(width), getSize(height));
    layoutParams.gravity = gravity;
    return layoutParams;
  }

  public static LinearLayout.LayoutParams createLinear(int width, int height, float weight) {
    return new LinearLayout.LayoutParams(getSize(width), getSize(height), weight);
  }

  public static LinearLayout.LayoutParams createLinear(int width, int height) {
    return new LinearLayout.LayoutParams(getSize(width), getSize(height));
  }
}
