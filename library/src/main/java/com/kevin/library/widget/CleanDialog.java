package com.kevin.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.kevin.library.R;
import com.kevin.library.widget.builder.IconFlag;
import com.kevin.library.widget.builder.NegativeClickListener;
import com.kevin.library.widget.builder.PositiveClickListener;

/**
 * Class: CleanDialog </br>
 * Description: 项目中使用的dialog </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 16/9/14 上午9:36</br>
 * Update: 16/9/14 上午9:36 </br>
 **/

public class CleanDialog extends Dialog implements View.OnClickListener {

    @ColorInt
    private int mNegativeTextColor;
    @ColorInt
    private int mPositiveTextColor;
    @StyleRes
    private int mStyle;
    @DrawableRes
    private int mDrawableImage;
    private IconFlag mIconFlag;
    private String mTitle;
    private String mNegativeButton;
    private String mPositiveButton;
    private NegativeClickListener mNegativeClickListener;
    private PositiveClickListener mPositiveClickListener;
    private Drawable mPositiveDrawable;
    private Drawable mNegativeDrawable;

    private AppCompatImageView mImageView;
    private AppCompatTextView mTextView;
    private AppCompatButton mPositiveBtn;
    private AppCompatButton mNegativeBtn;


    private CleanDialog(Context context) {
        super(context);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.view_dialog_sure){
            if (mPositiveClickListener!=null){
                mPositiveClickListener.onPositiveClickListener(CleanDialog.this);
            }else {
                throw  new NullPointerException("PositiveClickListener can not null");
            }
        }else {
            if (mNegativeClickListener!=null){
                mNegativeClickListener.onNegativeClickListener(CleanDialog.this);
            }else {
                throw new NullPointerException("NegativeClickListener can not null");
            }
        }
    }


    public static class Builder {

        @ColorInt
        int negativeTextColor;
        @ColorInt
        int positiveTextColor;
        @StyleRes
        public int style;
        @DrawableRes
        private int drawableImage;
        IconFlag iconFlag = IconFlag.OK;
        public String title;
        String negativeButton;
        String positiveButton;
        NegativeClickListener negativeClickListener;
        PositiveClickListener positiveClickListener;
        public Context context;
        private Drawable positiveDrawable;
        private Drawable negativeDrawable;


        public Builder(Context context) {
            this.context = context;
        }

        public CleanDialog builder() {
            CleanDialog dialog = new CleanDialog(context);
            dialog.mIconFlag = iconFlag;
            dialog.mTitle = title;
            dialog.mNegativeButton = negativeButton;
            dialog.mPositiveButton = positiveButton;
            dialog.mNegativeClickListener = negativeClickListener;
            dialog.mPositiveClickListener = positiveClickListener;
            dialog.mNegativeTextColor = negativeTextColor;
            dialog.mPositiveTextColor = positiveTextColor;
            dialog.mStyle = style;
            dialog.mDrawableImage = drawableImage;
            dialog.mNegativeDrawable = negativeDrawable;
            dialog.mPositiveDrawable = positiveDrawable;
            return dialog;
        }

        public Builder iconFlag(IconFlag flag) {
            iconFlag = flag;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder negativeButton(String text, NegativeClickListener negativeClickListener) {
            negativeButton = text;
            this.negativeClickListener = negativeClickListener;
            return this;
        }

        public Builder positiveButton(String text, PositiveClickListener positiveClickListener) {
            positiveButton = text;
            this.positiveClickListener = positiveClickListener;
            return this;
        }

        public Builder negativeTextColor(@ColorInt int negativeTextColor) {
            this.negativeTextColor = negativeTextColor;
            return this;
        }

        public Builder positiveTextColor(@ColorInt int positiveTextColor) {
            this.positiveTextColor = positiveTextColor;
            return this;
        }

        public Builder style(@StyleRes int style) {
            this.style = style;
            return this;
        }

        public Builder positiveDrawable(Drawable drawable){
            this.positiveDrawable = drawable;
            return this;
        }

        public Builder negativeDrawable(Drawable drawable){
            this.negativeDrawable = drawable;
            return this;
        }

        public Builder drawableRes(@DrawableRes int res){
            this.drawableImage = res;
            return this;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_custom_dialog);
        mImageView = (AppCompatImageView) findViewById(R.id.image_dialog);
        mTextView = (AppCompatTextView) findViewById(R.id.view_dialog_av_text);
        mPositiveBtn = (AppCompatButton) findViewById(R.id.view_dialog_sure);
        mNegativeBtn = (AppCompatButton) findViewById(R.id.view_dialog_cancel);
        pushViewValue();
    }

    private void pushViewValue(){
        mTextView.setText(mTitle);
        mPositiveBtn.setText(mPositiveButton);
        mNegativeBtn.setText(mNegativeButton);
        mPositiveBtn.setOnClickListener(this);
        mNegativeBtn.setOnClickListener(this);
        if (mPositiveTextColor!=0){
            mPositiveBtn.setTextColor(mPositiveTextColor);
        }
        if (mNegativeTextColor!=0){
            mNegativeBtn.setTextColor(mNegativeTextColor);
        }
        if (mPositiveDrawable!=null){
            mPositiveBtn.setBackgroundDrawable(mPositiveDrawable);
        }
        if (mNegativeDrawable!=null){
            mNegativeBtn.setBackgroundDrawable(mNegativeDrawable);
        }

        mImageView.setImageResource(mIconFlag.getImage());
        if (mDrawableImage!=0){
            mImageView.setImageResource(mDrawableImage);
        }
    }

    public void showDialog() {
        Window win = getWindow();
        if (win != null) {
            setCancelable(false);
            if (mStyle!=0){
                win.setWindowAnimations(mStyle);
            }else {
                win.setWindowAnimations(R.style.dialogWindowAnim);
            }
        }
        this.show();
        WindowManager manager = win.getWindowManager();
        Display display = manager.getDefaultDisplay();
        WindowManager.LayoutParams p = win.getAttributes();
        p.width = display.getWidth();
        onWindowAttributesChanged(p);
        getWindow().setAttributes(p);
    }
}
