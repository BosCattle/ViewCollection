package com.kevin.android.ui.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Class: MyLayout </br>
 * Description: 自定义viewgroup,测试viewgroup事件分发机制 </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 16/9/12 上午12:02</br>
 * Update: 16/9/12 上午12:02 </br>
 **/

public class MyLayout  extends LinearLayout{
    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
