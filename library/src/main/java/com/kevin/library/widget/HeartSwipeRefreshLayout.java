package com.kevin.library.widget;

import android.content.Context;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/*
 * Class: HeartSwipeRefreshLayout </br>
 * Description: 桃心的下拉刷新控件 </br>
 * Creator: Kevin </br>
 * Date: 2016/8/25 0:26 </br>
 * Update: 2016/8/25 2016/8/25 </br>
 */

public class HeartSwipeRefreshLayout extends ViewGroup implements NestedScrollingParent,
    NestedScrollingChild {
  public HeartSwipeRefreshLayout(Context context) {
    super(context);
  }

  public HeartSwipeRefreshLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public HeartSwipeRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public HeartSwipeRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

  }



  public interface OnRefreshListener {
    /**
     * Called when a swipe gesture triggers a refresh.
     */
    void onRefresh();
  }

  public interface OnChildScrollUpCallback {
    /**
     * Callback that will be called when {@link SwipeRefreshLayout#canChildScrollUp()} method
     * is called to allow the implementer to override its behavior.
     *
     * @param parent SwipeRefreshLayout that this callback is overriding.
     * @param child The child view of SwipeRefreshLayout.
     *
     * @return Whether it is possible for the child view of parent layout to scroll up.
     */
    boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child);
  }
}
