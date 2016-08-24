package com.kevin.library.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;

import com.kevin.library.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class: HeartFlowerView </br>
 * Description: 心形仙女散花效果 </br>
 * Creator: Kevin </br>
 * Date: 2016/8/19 18:46 </br>
 * Update: 2016/8/19 2016/8/19 </br>
 */

public class HeartFlowerView extends View implements ValueAnimator.AnimatorUpdateListener {

    private float mPhaseOne;
    private float mPhaseTwo;
    private float mPhaseThree;
    private HeartMotion mHeartMotion;
    private int mWidth = 0;
    public int mHeight = 0;
    private List<Flower> mFlowerOne;
    private List<Flower> mFlowerTwo;
    private List<Flower> mFlowerThree;
    // 曲线高度分割
    private int mQuadCount = 10;
    //曲线的偏转角度
    private float mIntensity = 0.2f;
    private int mRange = 70;
    private int mTop = 20;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private Bitmap mBitmap;
    private int mFlowerCount = 4;

    public HeartFlowerView(Context context) {
        super(context);
        initialization(context);
    }

    /**
     * 初始化数据
     *
     * @param context
     */
    private void initialization(Context context) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_heart_motion);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = windowManager.getDefaultDisplay().getWidth();
        mHeight = (int) (windowManager.getDefaultDisplay().getHeight() * (3 / 2f));
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPathMeasure = new PathMeasure();
        buildFlower(mFlowerCount, mFlowerOne);
        buildFlower(mFlowerCount, mFlowerTwo);
        buildFlower(mFlowerCount, mFlowerThree);
    }

    /**
     * @param mFlowerCount 花的数量
     * @param mFlowerOne   构建花的路径list
     */
    private void buildFlower(int mFlowerCount, List<Flower> mFlowerOne) {
        int max = (int) (mWidth * 3 / 4f);
        int min = (int) (mWidth / 4f);
        Random random = new Random();
        for (int i = 0; i < mFlowerCount; i++) {
            int num = random.nextInt(max) % (max - min + 1) + min;
            Path path = new Path();
            Cpoint point = new Cpoint(num, 0);
            List<Cpoint> cPoint = buildPath(point);
            drawFlowPath(path, cPoint);
            Flower flower = new Flower();
            flower.setmPath(path);
            mFlowerOne.add(flower);
        }
    }

    /**
     * build single flower path
     *
     * @param path   路径
     * @param cPoint 途径点列表
     */
    private void drawFlowPath(Path path, List<Cpoint> cPoint) {
        if (cPoint.size() > 1) {
            for (int j = 0; j < cPoint.size(); j++) {
                Cpoint point = cPoint.get(j);
                if (j == 0) {
                    Cpoint next = cPoint.get(j + 1);
                    point.mDx = (next.mX - point.mX) * mIntensity;
                    point.mDy = (next.mY - point.mY) * mIntensity;
                } else if (j == cPoint.size() - 1) {
                    Cpoint prev = cPoint.get(j - 1);
                    point.mDx = (point.mX - prev.mX) * mIntensity;
                    point.mDy = (point.mY - prev.mY) * mIntensity;
                } else {
                    Cpoint next = cPoint.get(j + 1);
                    Cpoint prev = cPoint.get(j - 1);
                    point.mDx = (next.mX - prev.mX) * mIntensity;
                    point.mDy = (next.mY - prev.mY) * mIntensity;
                }
                if (j == 0) {
                    path.moveTo(point.mX, point.mY);
                } else {
                    Cpoint prev = cPoint.get(j - 1);
                    path.cubicTo(prev.mX + prev.mDx, prev.mY + prev.mDy, point.mX - point.mDx,
                            point.mY - point.mDy, point.mX, point.mY);

                }
            }
        } else {
            throw new RuntimeException("cPoint is invalid");
        }
    }

    /**
     * build path point list
     *
     * @param point single point
     * @return
     */
    private List<Cpoint> buildPath(Cpoint point) {
        List<Cpoint> points = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < mQuadCount; i++) {
            if (i == 0) {
                points.add(point);
            } else {
                Cpoint tmpCpoint = new Cpoint(0, 0);
                if (random.nextInt(100) % 2 == 0) {
                    tmpCpoint.mX = point.mX + random.nextInt(mRange);
                } else {
                    tmpCpoint.mX = point.mY + random.nextInt(mRange);
                }
                tmpCpoint.mY = mHeight / (mQuadCount * i);
            }
        }
        return points;
    }

    public HeartFlowerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialization(context);
    }

    public HeartFlowerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawFlowers(canvas, mFlowerTwo);
        drawFlowers(canvas, mFlowerTwo);
        drawFlowers(canvas, mFlowerThree);
        invalidate();
    }

    /**
     * @param canvas
     * @param mFlowerTwo
     */
    private void drawFlowers(Canvas canvas, List<Flower> mFlowerTwo) {
        for (Flower f :
                mFlowerTwo) {
            float[] pos = new float[2];
            mPathMeasure.setPath(f.getmPath(), false);
            mPathMeasure.getPosTan(mHeight * f.getmValue(), pos, null);
            canvas.drawBitmap(mBitmap, pos[0], pos[1] - mTop, null);
        }
    }

    public void startAnimation(int times, int singleTime, int delay) {
        ObjectAnimator mAnimationA = ObjectAnimator.ofFloat(this, "aniation1", 0f, 1f);
        mAnimationA.setDuration(singleTime);
        mAnimationA.addUpdateListener(this);
        mAnimationA.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator mAnimationB = ObjectAnimator.ofFloat(this, "aniation2", 0f, 1f);
        mAnimationB.setDuration(singleTime);
        mAnimationB.addUpdateListener(this);
        mAnimationB.setInterpolator(new AccelerateInterpolator());
        mAnimationB.setStartDelay(delay);

        ObjectAnimator mAnimationC = ObjectAnimator.ofFloat(this, "aniation3", 0f, 1f);
        mAnimationC.setDuration(singleTime);
        mAnimationC.addUpdateListener(this);
        mAnimationC.setInterpolator(new AccelerateInterpolator());
        mAnimationC.setStartDelay(delay * 2);

        mAnimationB.start();
        mAnimationA.start();
        mAnimationC.start();
    }

    /**
     * Set animation callback
     *
     * @param varMotion
     */
    public void setHeartMotion(HeartMotion varMotion) {
        mHeartMotion = varMotion;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        updateValue(mPhaseOne, mFlowerOne);
        updateValue(mPhaseOne, mFlowerTwo);
        updateValue(mPhaseOne, mFlowerThree);
        invalidate();
    }

    private void updateValue(float mPhaseOne, List<Flower> mFlowerOne) {
        for (Flower flower :
                mFlowerOne) {
            flower.setmValue(mPhaseOne);
        }
    }

  public void setmFlowerOne(List<Flower> mFlowerOne) {
    this.mFlowerOne = mFlowerOne;
  }

  public void setmFlowerTwo(List<Flower> mFlowerTwo) {
    this.mFlowerTwo = mFlowerTwo;
  }

  public void setmFlowerThree(List<Flower> mFlowerThree) {
    this.mFlowerThree = mFlowerThree;
  }

  /**
     * Interface: HeartMotion </br>
     * Description:  </br>
     * Creator: Kevin </br>
     * Date: 2016/8/19 18:54 </br>
     * Update: 2016/8/19 2016/8/19 </br>
     */
    public interface HeartMotion {

        /**
         * @param times 次数
         * @param time  每次的时长
         * @param delay 每个动画的延迟
         */
        void onStart(int times, int time, int delay);

        /**
         * Every time you run the animation
         * 每次运行动画
         */
        void onTick();

        /**
         * Animation finish
         * 动画完成
         */
        void onFinish();
    }

    /**
     * Class: Flower </br>
     * Description:  Flower model</br>
     * Creator: Kevin </br>
     * Date: 2016/8/19 19:07 </br>
     * Update: 2016/8/19 2016/8/19 </br>
     */
    public class Flower {

        private Path mPath;
        private float mValue;

        public Path getmPath() {
            return mPath;
        }

        public void setmPath(Path mPath) {
            this.mPath = mPath;
        }

        public float getmValue() {
            return mValue;
        }

        public void setmValue(float mValue) {
            this.mValue = mValue;
        }
    }

    /**
     * Class: Cpoint </br>
     * Description:  </br>
     * Creator: Kevin </br>
     * Date: 2016/8/20 9:56 </br>
     * Update: 2016/8/20 2016/8/20 </br>
     */
    public class Cpoint {
        public float mX = 0f;
        public float mY = 0f;
        public float mDx = 0f;
        public float mDy = 0f;

        public Cpoint(float x, float y) {
            this.mX = x;
            this.mDy = y;
        }
    }
}
