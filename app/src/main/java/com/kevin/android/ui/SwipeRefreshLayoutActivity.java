package com.kevin.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kevin.android.R;
import com.kevin.library.util.TimeBuilder;
import com.kevin.library.widget.LimitTime;

/**
 * 下拉刷新测试类
 */
public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        new LimitTime(this).setTimeBuilder(new TimeBuilder.Builder().delayTime(1).builder());
    }
}
