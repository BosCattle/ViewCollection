package com.kevin.library.widget.builder;

import android.support.annotation.DrawableRes;

import com.kevin.library.R;

/**
 * Class: IconFlag </br>
 * Description: 图标标志 </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 16/9/14 上午9:59</br>
 * Update: 16/9/14 上午9:59 </br>
 **/

public enum  IconFlag {
    INFO(1),ERROR(2),WARN(3);

    public int mFlag;

    private IconFlag(int flag){
        mFlag = flag;
    }

    @DrawableRes public int getImage(){
        switch (mFlag){
            case 1:
                return R.mipmap.ic_dialog_info;
            case 2:
                return R.mipmap.ic_dialog_info;
            case 3:
                return R.mipmap.ic_dialog_info;
        }
        return 0;
    }
}
