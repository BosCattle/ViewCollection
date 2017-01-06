package com.kevin.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.kevin.library.R;

/**
 * Class: SlideView </br>
 * Description: 自定义左滑效果 </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 12/11/2016 11:50 PM</br>
 * Update: 12/11/2016 11:50 PM </br>
 **/

public class SlideView extends RelativeLayout {

  private Context mContext;
  private Scroller mScroller;
  private View mRootView;
  private int mMinScrollWidth = 200;

  public SlideView(Context context) {
    super(context);
    init();
  }

  public SlideView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public void init(){
    mContext = getContext();
    mScroller = new Scroller(mContext);
    mRootView = View.inflate(mContext, R.layout.view_slide_view,this);
  }
}
