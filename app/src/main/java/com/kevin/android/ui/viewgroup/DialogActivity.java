package com.kevin.android.ui.viewgroup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kevin.android.R;
import com.kevin.android.ui.SwipeRefreshLayoutActivity;
import com.kevin.library.widget.CleanDialog;
import com.kevin.library.widget.builder.IconFlag;
import com.kevin.library.widget.builder.NegativeClickListener;
import com.kevin.library.widget.builder.PositiveClickListener;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        final CleanDialog dialog = new CleanDialog.Builder(this).iconFlag(IconFlag.INFO).negativeButton("取消", new NegativeClickListener() {
            @Override
            public void onNegativeClickListener() {
            }
        }).positiveButton("确定", new PositiveClickListener() {
            @Override
            public void onPositiveClickListener() {
                Intent intent = new Intent(DialogActivity.this, SwipeRefreshLayoutActivity.class);
                startActivity(intent);
            }
        }).title("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈").builder();
        dialog.showDialog(dialog);
    }

    public static void startDialog(Activity activity) {
        Intent intent = new Intent(activity, DialogActivity.class);
        activity.startActivity(intent);
    }
}
