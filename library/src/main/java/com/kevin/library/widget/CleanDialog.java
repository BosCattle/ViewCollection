package com.kevin.library.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.kevin.library.R;
import com.kevin.library.widget.builder.IconFlag;
import com.kevin.library.widget.builder.NegativeClickListener;
import com.kevin.library.widget.builder.PositiveClickListener;

/**
 * Class: CleanDialog </br>
 * Description: 干净的dialog </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 16/9/14 上午9:36</br>
 * Update: 16/9/14 上午9:36 </br>
 **/

public  class CleanDialog extends AlertDialog {

    public IconFlag mIconFlag;
    public String mTitle;
    public String mNegativeButton;
    public String mPositiveButton;
    public NegativeClickListener mNegativeClickListener;
    public PositiveClickListener mPositiveClickListener;
    @ColorInt public int mNegativeTextColor;
    @ColorInt public int mPositiveTextColor;
    @DrawableRes public int mStyle;
    public Context mContext;


    protected CleanDialog(Context context) {
        super(context);
    }

    protected CleanDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected CleanDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        public IconFlag iconFlag;
        public String title;
        public String negativeButton;
        public String positiveButton;
        public NegativeClickListener negativeClickListener;
        public PositiveClickListener positiveClickListener;
        @ColorInt public int negativeTextColor;
        @ColorInt public int positiveTextColor;
        @DrawableRes public int style;
        public Context context;

        public CleanDialog builder(Context context){
            CleanDialog dialog = new CleanDialog(context);
            dialog.mContext = context;
            dialog.mIconFlag = iconFlag;
            dialog.mTitle = title;
            dialog.mNegativeButton = negativeButton;
            dialog.mPositiveButton = positiveButton;
            dialog.mNegativeClickListener = negativeClickListener;
            dialog.mPositiveClickListener = positiveClickListener;
            dialog.mNegativeTextColor = negativeTextColor;
            dialog.mPositiveTextColor = positiveTextColor;
            dialog.mStyle  = style;
            return dialog;
        }

        public  void initialization() {
            ViewGroup root = (ViewGroup) ((Activity)context).getWindow().getDecorView().getRootView();
            View view = LayoutInflater.from(context).inflate(R.layout.view_custom_dialog,root,false);
            AppCompatImageView image = (AppCompatImageView) view.findViewById(R.id.image_dialog);
            AppCompatTextView textview = (AppCompatTextView) view.findViewById(R.id.view_dialog_av_text);
            AppCompatButton mPositiveButton = (AppCompatButton) view.findViewById(R.id.view_dialog_sure);
            final AppCompatButton mNegativeButton = (AppCompatButton) view.findViewById(R.id.view_dialog_cancel);
            if (mPositiveButton!=null) {
                mPositiveButton.setText(positiveButton);
            }
            if (mNegativeButton!=null) {
                mNegativeButton.setText(negativeButton);
            }
            if (positiveTextColor!=0){
                mPositiveButton.setTextColor(positiveTextColor);
            }
            if (negativeTextColor!=0){
                mNegativeButton.setTextColor(negativeTextColor);
            }
            if (positiveClickListener!=null){
                mPositiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        positiveClickListener.onPositiveClickListener();
                    }
                });
            }
            if (negativeClickListener!=null){
                mNegativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeClickListener.onNegativeClickListener();
                    }
                });
            }
            image.setImageResource(iconFlag.getImage());
            textview.setText(title);
        }

        public Builder iconFlag(IconFlag flag){
            iconFlag = flag;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder negativeButton(String text){
            negativeButton = text;
            return this;
        }

        public Builder positiveButton(String text){
            positiveButton = text;
            return this;
        }

        public Builder negativeClickListener(NegativeClickListener negativeClickListener){
            this.negativeClickListener = negativeClickListener;
            return this;
        }

        public Builder positiveClickListener(PositiveClickListener positiveClickListener){
            this.positiveClickListener = positiveClickListener;
            return this;
        }

        public Builder negativeTextColor(@ColorInt int negativeTextColor){
            this.negativeTextColor = negativeTextColor;
            return this;
        }

        public Builder positiveTextColor(@ColorInt int positiveTextColor){
            this.positiveTextColor = positiveTextColor;
            return this;
        }

        public Builder style(@DrawableRes int style){
            this.style = style;
            return this;
        }


    }

    public void showDialog(){
        final CleanDialog dialog = new CleanDialog(mContext);
        Window win = dialog.getWindow();
        win.setWindowAnimations(R.style.dialogWindowAnim);
        dialog.show();
    }
}
