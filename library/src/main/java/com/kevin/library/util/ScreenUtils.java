package com.kevin.library.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * Created by kevin on 16-6-10.
 */
public final class ScreenUtils {

  private static DisplayMetrics metrics;

  public static DisplayMetrics getDisplayMetrics(Context context) {
    if (metrics == null) {
      metrics = context.getResources().getDisplayMetrics();
    }

    return metrics;
  }

  public static float getDisplayWidth(Context context) {
    DisplayMetrics dm = context.getResources().getDisplayMetrics();
    return dm.widthPixels;
  }

  public static float getDisplayHeight(Context context) {
    DisplayMetrics dm = context.getResources().getDisplayMetrics();
    return dm.heightPixels;
  }

  public static int px2dp(Context context, float pxValue) {
    if (metrics == null) {
      getDisplayMetrics(context);
    }
    return (int) (pxValue / metrics.density + 0.5f);
  }

  public static int dp2px(Context context, float dipValue) {
    if (metrics == null) {
      getDisplayMetrics(context);
    }
    return (int) (dipValue * metrics.density + 0.5f);
  }

  public static int dp2px(float dpValue, Context context) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static int px2sp(Context context, float pxValue) {
    if (metrics == null) {
      getDisplayMetrics(context);
    }
    return (int) (pxValue / metrics.scaledDensity + 0.5f);
  }

  public static int sp2px(Context context, float spValue) {
    if (metrics == null) {
      getDisplayMetrics(context);
    }
    return (int) (spValue * metrics.scaledDensity + 0.5f);
  }

  public static float getTextLength(float textSize, String text) {
    Paint paint = new Paint();
    paint.setTextSize(textSize);
    return paint.measureText(text);
  }

  public static int[] getRealMetrics(Activity activity) {
    int[] dpi = new int[2];
    Display display = activity.getWindowManager().getDefaultDisplay();
    DisplayMetrics dm = new DisplayMetrics();
    @SuppressWarnings("rawtypes") Class c;
    try {
      c = Class.forName("android.view.Display");
      @SuppressWarnings("unchecked") Method method =
          c.getMethod("getRealMetrics", DisplayMetrics.class);
      method.invoke(display, dm);
      dpi[0] = dm.widthPixels;
      dpi[1] = dm.heightPixels;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dpi;
  }

  public static int getStatusHeight(Activity activity) {
    Rect rect = new Rect();
    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
    return rect.top;
  }
}
