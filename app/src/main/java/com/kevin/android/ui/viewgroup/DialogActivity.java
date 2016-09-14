package com.kevin.android.ui.viewgroup;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kevin.android.R;
import com.kevin.library.widget.CleanDialog;
import com.kevin.library.widget.builder.IconFlag;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        CleanDialog dialog = new CleanDialog.Builder().iconFlag(IconFlag.INFO).negativeButton("取消").positiveButton("确定").title("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈").builder(this);
        dialog.showDialog();
    }

    public static void startDialog(Activity activity){
        Intent intent = new Intent(activity,DialogActivity.class);
        activity.startActivity(intent);
    }
}
