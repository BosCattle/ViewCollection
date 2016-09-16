package com.kevin.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.kevin.library.R;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Class: MaskLayerDialog </br>
 * Description: 带有蒙层的dialog,在加载视频,大量数据时特别有效 </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 16/9/16 下午11:55</br>
 * Update: 16/9/16 下午11:55 </br>
 **/

public class MaskLayerDialog extends Dialog{

    private ProgressWheel mProgressWheel;

    public MaskLayerDialog(Context context) {
        this(context,0);
    }

    private MaskLayerDialog(Context context, int themeResId) {
        super(context, R.style.Dialog_Fullscreen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mask_layer_dialog);
        mProgressWheel = (ProgressWheel) findViewById(R.id.view_progress_wheel);
    }

    @Override
    public void show() {
        super.show();
        mProgressWheel.spin();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mProgressWheel.stopSpinning();
    }
}
