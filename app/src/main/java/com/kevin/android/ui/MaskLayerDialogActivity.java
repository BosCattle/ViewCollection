package com.kevin.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kevin.android.R;
import com.kevin.library.widget.MaskLayerDialog;

public class MaskLayerDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask_layer_dialog);
        new MaskLayerDialog(this).show();
    }

    public static void startMaskLayer(Activity activity){
        Intent intent = new Intent(activity,MaskLayerDialogActivity.class);
        activity.startActivity(intent);
    }
}
