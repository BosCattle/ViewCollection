package com.kevin.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.kevin.android.R;
import com.kevin.library.widget.HeartFlowerView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Class: FlowerActivity </br>
 * Description:  仙女散花效果</br>
 * Creator: Kevin </br>
 * Date: 2016/8/20 17:50 </br>
 * Update: 2016/8/20 2016/8/20 </br>
 */
public class FlowerActivity extends AppCompatActivity {

    public static final String TAG = FlowerActivity.class.getSimpleName();

    HeartFlowerView flowerFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        flowerFlower = (HeartFlowerView) findViewById(R.id.flower_flower);
        flowerFlower.startAnimation();
    }

    public static void startFlower(AppCompatActivity activity) {
        Intent intent = new Intent(activity, FlowerActivity.class);
        activity.startActivity(intent);
    }
}
