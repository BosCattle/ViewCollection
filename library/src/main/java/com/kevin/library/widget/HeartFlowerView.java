package com.kevin.library.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
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

    /**
     * 动画改变的属性值
     */
    private float phase1 = 0f;
    private float phase2 = 0f;
    private float phase3 = 0f;

    private int mFlowerCount = 4;

    private int width = 0;
    private int height = 0;

    private List<Fllower> flower1 = new ArrayList<>();
    private List<Fllower> flower2 = new ArrayList<>();
    private List<Fllower> flower3 = new ArrayList<>();

    private int time = 4000;
    private int delay = 500;

    //曲线高度个数分割
    private int quadCount = 10;
    //曲度
    private float intensity = 0.2f;

    //曲线摇摆的幅度
    private int range = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());

    private int top = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

    private Paint mPaint;
    private PathMeasure pathMeasure = null;

    private Bitmap mBitmap;

    public HeartFlowerView(Context context) {
        super(context);
        init(context);
    }

    public HeartFlowerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = (int) (wm.getDefaultDisplay().getHeight() * 3 / 2f);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);

        pathMeasure = new PathMeasure();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_heart_motion);

        builderFollower(mFlowerCount, flower1);
        builderFollower(mFlowerCount, flower2);
        builderFollower(mFlowerCount, flower3);
    }


    /**
     * 创建花
     */
    private void builderFollower(int count, List<Fllower> flower) {

        int max = (int) (width * 3 / 4f);
        int min = (int) (width / 4f);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int s = random.nextInt(max) % (max - min + 1) + min;
            Path path = new Path();
            CPoint cPoint = new CPoint(s, 0);
            List<CPoint> points = builderPath(cPoint);
            drawFllowerPath(path, points);
            Fllower fllower = new Fllower();
            fllower.setPath(path);
            flower.add(fllower);
        }

    }

    /**
     * 画曲线
     *
     * @param path
     * @param points
     */
    private void drawFllowerPath(Path path, List<CPoint> points) {
        if (points.size() > 1) {
            for (int j = 0; j < points.size(); j++) {

                CPoint point = points.get(j);

                if (j == 0) {
                    CPoint next = points.get(j + 1);
                    point.dx = ((next.x - point.x) * intensity);
                    point.dy = ((next.y - point.y) * intensity);
                } else if (j == points.size() - 1) {
                    CPoint prev = points.get(j - 1);
                    point.dx = ((point.x - prev.x) * intensity);
                    point.dy = ((point.y - prev.y) * intensity);
                } else {
                    CPoint next = points.get(j + 1);
                    CPoint prev = points.get(j - 1);
                    point.dx = ((next.x - prev.x) * intensity);
                    point.dy = ((next.y - prev.y) * intensity);
                }

                // create the cubic-spline path
                if (j == 0) {
                    path.moveTo(point.x, point.y);
                } else {
                    CPoint prev = points.get(j - 1);
                    path.cubicTo(prev.x + prev.dx, (prev.y + prev.dy),
                            point.x - point.dx, (point.y - point.dy),
                            point.x, point.y);
                }
            }
        }
    }


    /**
     * 画路径
     *
     * @param point
     * @return
     */
    private List<CPoint> builderPath(CPoint point) {
        List<CPoint> points = new ArrayList<CPoint>();
        Random random = new Random();
        for (int i = 0; i < quadCount; i++) {
            if (i == 0) {
                points.add(point);
            } else {
                CPoint tmp = new CPoint(0, 0);
                if (random.nextInt(100) % 2 == 0) {
                    tmp.x = point.x + random.nextInt(range);
                } else {
                    tmp.x = point.x - random.nextInt(range);
                }
                tmp.y = (int) (height / (float) quadCount * i);
                points.add(tmp);
            }
        }
        return points;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawFllower(canvas, flower1);
        drawFllower(canvas, flower2);
        drawFllower(canvas, flower3);

    }

    /**
     * @param canvas
     * @param flower
     */
    private void drawFllower(Canvas canvas, List<Fllower> flower) {
        for (Fllower fllower : flower) {
            float[] pos = new float[2];

//            canvas.drawPath(fllower.getPath(), mPaint);

            pathMeasure.setPath(fllower.getPath(), false);
            pathMeasure.getPosTan(height * fllower.getValue(), pos, null);
            canvas.drawBitmap(mBitmap, pos[0], pos[1] - top, null);
        }
    }

    public void startAnimation() {
        ObjectAnimator mAnimator1 = ObjectAnimator.ofFloat(this, "phase1", 0f,
                1f);
        mAnimator1.setDuration(time);
        mAnimator1.addUpdateListener(this);
        mAnimator1.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator mAnimator2 = ObjectAnimator.ofFloat(this, "phase2", 0f,
                1f);
        mAnimator2.setDuration(time);
        mAnimator2.addUpdateListener(this);
        mAnimator2.setInterpolator(new AccelerateInterpolator());
        mAnimator2.setStartDelay(delay);

        ObjectAnimator mAnimator3 = ObjectAnimator.ofFloat(this, "phase3", 0f,
                1f);
        mAnimator3.setDuration(time);
        mAnimator3.addUpdateListener(this);
        mAnimator3.setInterpolator(new AccelerateInterpolator());
        mAnimator3.setStartDelay(delay * 2);

        mAnimator2.start();
        mAnimator1.start();
        mAnimator3.start();
    }

    public void setTimes(int times, int singleTime, int delay) {
        for (int i = 0; i < times; i++) {
            ObjectAnimator mAnimator1 = ObjectAnimator.ofFloat(this, "phase1", 0f, 1f);
            mAnimator1.setDuration(singleTime);
            mAnimator1.addUpdateListener(this);
            mAnimator1.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator mAnimator2 = ObjectAnimator.ofFloat(this, "phase2", 0f, 1f);
            mAnimator2.setDuration(singleTime);
            mAnimator2.addUpdateListener(this);
            mAnimator2.setInterpolator(new AccelerateInterpolator());
            mAnimator2.setStartDelay(delay);

            ObjectAnimator mAnimator3 = ObjectAnimator.ofFloat(this, "phase3", 0f, 1f);
            mAnimator3.setDuration(singleTime);
            mAnimator3.addUpdateListener(this);
            mAnimator3.setInterpolator(new AccelerateInterpolator());
            mAnimator3.setStartDelay(delay * 2);

            mAnimator2.start();
            mAnimator1.start();
            mAnimator3.start();
        }
    }

    private void updateValue(float value, List<Fllower> flower) {
        for (Fllower fllower : flower) {
            fllower.setValue(value);
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator arg0) {
            updateValue(getPhase1(), flower1);
            updateValue(getPhase2(), flower2);
            updateValue(getPhase3(), flower3);
            invalidate();
    }

    public float getPhase1() {
        return phase1;
    }

    public void setPhase1(float phase1) {
        this.phase1 = phase1;
    }

    public float getPhase2() {
        return phase2;
    }

    public void setPhase2(float phase2) {
        this.phase2 = phase2;
    }

    public float getPhase3() {
        return phase3;
    }

    public void setPhase3(float phase3) {
        this.phase3 = phase3;

    }

    public class CPoint {

        float x = 0f;
        float y = 0f;

        float dx = 0f;
        float dy = 0f;

        CPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Fllower {

        private Path mPath;

        private float value;

        public Path getPath() {
            return mPath;
        }

        public void setPath(Path mPath) {
            this.mPath = mPath;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

}
