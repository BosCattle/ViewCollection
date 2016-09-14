package com.kevin.android.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kevin.android.R;
import com.kevin.android.ui.viewgroup.MaterialProgressDrawable;
import com.kevin.android.ui.viewgroup.MyLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EventDistributionActivity extends AppCompatActivity {

    private static final String TAG = EventDistributionActivity.class.getSimpleName();
    @InjectView(R.id.btn_event)
    Button mBtnEvent;
    @InjectView(R.id.activity_event_distribution)
    MyLayout mActivityEventDistribution;
    @InjectView(R.id.view_group_one)
    Button mViewGroupOne;
    @InjectView(R.id.view_group_two)
    Button mViewGroupTwo;
    @InjectView(R.id.custom_layout)
    MyLayout mCustomLayout;
    @InjectView(R.id.image_drawable)
    ImageView mImageDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_distribution);
        ButterKnife.inject(this);
        eventOnClick();
    }

    private void eventOnClick() {
        mBtnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: execute");
            }
        });
        mBtnEvent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG, "onTouch: execute,action=" + motionEvent.getAction());
                return true;
            }
        });

        mCustomLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG, "custom layout onTouch: ");
                return false;
            }
        });

        mViewGroupOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "you onClick: one");
            }
        });

        mViewGroupTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "you onClick: two");
            }
        });
        MaterialProgressDrawable drawable = new MaterialProgressDrawable(this,getWindow().getDecorView());
        mImageDrawable.setImageDrawable(drawable);
        drawable.setColorSchemeColors(Color.BLACK,Color.BLUE,Color.GREEN);
        drawable.start();
    }

    public static void startEventDistribution(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, EventDistributionActivity.class);
        appCompatActivity.startActivity(intent);
    }
}
