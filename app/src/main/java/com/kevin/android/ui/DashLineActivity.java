package com.kevin.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.kevin.android.R;

public class DashLineActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dash_line);
  }

  public static void startDashLine(Activity activity){
    Intent intent = new Intent(activity,DashLineActivity.class);
    activity.startActivity(intent);
  }
}
