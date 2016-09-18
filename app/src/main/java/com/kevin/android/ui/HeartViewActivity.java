package com.kevin.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.kevin.android.R;
import com.kevin.library.widget.heartview.HeartView;

public class HeartViewActivity extends AppCompatActivity {

    private HeartView mHeartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_view);
        mHeartView = (HeartView) findViewById(R.id.heart_view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHeartView.reDraw();
        return super.onTouchEvent(event);
    }

    public static void startHeartView(Activity activity){
        Intent intent = new Intent(activity,HeartViewActivity.class);
        activity.startActivity(intent);
    }
}
