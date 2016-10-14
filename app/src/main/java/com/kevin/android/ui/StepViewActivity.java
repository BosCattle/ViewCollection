package com.kevin.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.kevin.android.R;

public class StepViewActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_step_view);
    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(StepViewActivity.this,"别点我，妈的，智障",Toast.LENGTH_SHORT).show();
      }
    });
  }

  public static void startStepView(Activity activity){
    Intent intent = new Intent(activity,StepViewActivity.class);
    activity.startActivity(intent);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    findViewById(R.id.step_view).onTouchEvent(event);
    return super.onTouchEvent(event);
  }
}
