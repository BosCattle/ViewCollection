package com.kevin.library.util;

/**
 * Class: TimeBuilder </br>
 * Description: 时间构建类 </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 16/9/10 下午1:50</br>
 * Update: 16/9/10 下午1:50 </br>
 **/

public class TimeBuilder {

    public int mTimeInterval;
    public long mStartTime;
    public long mEndTime;
    public int mDelayTime;
    public LimitTimerCallBack mLimitTimerCallBack;

    public static final class Builder {
        public int timeInterval;
        public long startTime;
        public long endTime;
        public int delayTime;
        LimitTimerCallBack limitTimerCallBack;

        public TimeBuilder builder() {
            TimeBuilder timeBuilder = new TimeBuilder();
            timeBuilder.mTimeInterval = timeInterval;
            timeBuilder.mStartTime = startTime;
            timeBuilder.mEndTime = endTime;
            timeBuilder.mDelayTime = delayTime;
            timeBuilder.mLimitTimerCallBack = limitTimerCallBack;
            return timeBuilder;
        }

        public Builder timeInterval(int timeInterval) {
            this.timeInterval = timeInterval;
            return this;
        }

        public Builder startTime(long startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(long endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder delayTime(int delayTime) {
            this.delayTime = delayTime;
            return this;
        }

        public Builder limitTimerCallBack(LimitTimerCallBack limitTimerCallBack) {
            this.limitTimerCallBack = limitTimerCallBack;
            return this;
        }
    }

    interface LimitTimerCallBack {

        void onStart();

        void onTick(int time);

        void onInterrupt(int time);

        void onStop(int time);

        void onFinish(int time);
    }
}
