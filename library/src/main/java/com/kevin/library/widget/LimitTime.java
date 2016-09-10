package com.kevin.library.widget;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.kevin.library.util.TimeBuilder;

/**
 * Class: LimitTime </br>
 * Description:  </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 16/9/10 下午1:19</br>
 * Update: 16/9/10 下午1:19 </br>
 **/

public class LimitTime extends TextView {

    private TimeBuilder mTimeBuilder;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    public LimitTime(Context context) {
        super(context);
    }

    public LimitTime(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LimitTime(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LimitTime(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setTimeBuilder(TimeBuilder timeBuilder){
        mTimeBuilder = timeBuilder;
    }

    public void start(){
        if (mTimeBuilder!=null){
            if (mTimeBuilder.mLimitTimerCallBack!=null){

            }else {

            }
        }else {
            throw  new NullPointerException("TimeBuilder not be null.");
        }
    }
}
