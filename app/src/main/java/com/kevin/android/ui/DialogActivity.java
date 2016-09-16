package com.kevin.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kevin.android.R;
import com.kevin.library.widget.CleanDialog;
import com.kevin.library.widget.builder.IconFlag;
import com.kevin.library.widget.builder.NegativeClickListener;
import com.kevin.library.widget.builder.PositiveClickListener;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        final CleanDialog dialog = new CleanDialog.Builder(this).iconFlag(IconFlag.ERROR)
                .negativeButton("不去", new NegativeClickListener() {
                    @Override
                    public void onNegativeClickListener(CleanDialog dialog1) {
                        dialog1.dismiss();
                    }
                }).positiveButton("去嘛", new PositiveClickListener() {
                    @Override
                    public void onPositiveClickListener(CleanDialog dialog1) {
                        Intent intent = new Intent(DialogActivity.this, SwipeRefreshLayoutActivity.class);
                        startActivity(intent);
                        dialog1.dismiss();
                    }
                }).title("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈")
                .negativeTextColor(Color.BLACK).positiveTextColor(Color.BLACK).builder();
        dialog.showDialog();
    }

    public static void startDialog(Activity activity) {
        Intent intent = new Intent(activity, DialogActivity.class);
        activity.startActivity(intent);
    }
}
