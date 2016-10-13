package com.kevin.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.kevin.android.R;

public class StepViewActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_step_view);
  }

  public static void startStepView(Activity activity){
    Intent intent = new Intent(activity,StepViewActivity.class);
    activity.startActivity(intent);
  }
}
