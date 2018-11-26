package com.zero.floatview;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LinZewu
 * @time 16-3-30
 */
public class FloatView extends FlowBaseView {
    /**
     * 随机数
     */
    private float mMinStartX;
    private float mMidStartX;
    private float mMaxStartX;

    private float mMinStartY;
    private float mMaxStartY;

    private float mMinVx;
    private float mMaxVx;

    private float mMinVy;
    private float mMaxVy;

    private float mMinAccelerator;
    private float mMaxAccelerator;

    /**
     * 当前是否有动画正在展示
     */
    private boolean mIsShowing = false;

    /**
     * 动画产生间隔时间
     */
    private static final long ANIM_INTERVAL = 600;
    /**
     * 动画持续时间
     */
    private static final long ANIM_DURATION = 3;
    /**
     * 动画是否持续
     */
    private boolean mIsContinue = true;
    /**
     * 动画集合
     */
    private List<ValueAnimator> mAnimatorList;
    /**
     * 左边和右边两个动画，目的是增加表情数量，且不重叠
     */
    private static int DO_ANIM_LEFT = 1;
    private static int DO_ANIM_RIGHT = 2;
    /**
     * 动画Handler
     */
    private AnimHandler mHandler;

    /**
     * 动画Handler定义
     */
    private static class AnimHandler extends Handler {
        private WeakReference<FloatView> mWeakReference;

        public AnimHandler(FloatView floatView) {
            mWeakReference = new WeakReference<>(floatView);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == ANIM_MSG) {
                mWeakReference.get().createAnim(DO_ANIM_LEFT);
                mWeakReference.get().createAnim(DO_ANIM_RIGHT);
            }
        }
    }

    /**
     * 动画消息标志
     */
    private static final int ANIM_MSG = 0x99;
    /**
     * 间隔时间产生动画
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            while (mIsContinue) {
                try {
                    Thread.sleep(ANIM_INTERVAL);
                    Message msg = mHandler.obtainMessage();
                    msg.what = ANIM_MSG;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                }
            }
        }
    };


    public FloatView(Context context) {
        super(context);
        init(context);
    }

    public FloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mHandler = new AnimHandler(this);
        mAnimatorList = new ArrayList<>();

        //X轴弹出表情的起始位置
        mMinStartX = 0;
        mMidStartX = getScreenWidth(context) * 1 / 2;
        mMaxStartX = getScreenWidth(context) * 4 / 5;

        //Y轴弹出表情的起始位置
        mMinStartY = -getScreenHeight(context) * 4 / 5f;
        mMaxStartY = -getScreenHeight(context);

        mMinVx = (getScreenWidth(context) + mMaxStartX) / ANIM_DURATION;
        mMaxVx = getScreenWidth(context) * 1.2f;

        mMinVy = getScreenHeight(context) / 9f;
        mMaxVy = getScreenHeight(context) / 9f;

        mMinAccelerator = getScreenHeight(context) / 2;
        mMaxAccelerator = getScreenHeight(context) / 2;
    }

    /**
     * start
     */
    public void start() {
        new Thread(mRunnable).start();
        mIsShowing = true;
    }

    /**
     * stop
     */
    public void stop() {
        mIsContinue = false;
        mIsShowing = false;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (ValueAnimator animator : mAnimatorList) {
                    if (animator.isRunning()) {
                        animator.end();
                    }
                }
                mAnimatorList.clear();
            }
        }, 100);
    }

    /**
     * 挂载到某个Activity的最顶层
     *
     * @param activity
     */
    public void attachActivity(Activity activity) {
        ViewParent parent = getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ViewGroup parentView = (ViewGroup) parent;
            parentView.removeView(this);
        }
        FrameLayout decor = (FrameLayout) activity.getWindow().getDecorView();
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        decor.addView(this, lp);
        mIsShowing = true;
    }

    /**
     * 从某个Activity移除
     *
     * @param activity
     */
    public void disAttach(Activity activity) {
        FrameLayout decor = (FrameLayout) activity.getWindow().getDecorView();
        decor.removeView(this);
        mIsShowing = false;
    }

    /**
     * 是否正在展示
     *
     * @return
     */
    public boolean isShowing() {
        return mIsShowing;
    }

    /**
     * 创建动画
     */
    private void createAnim(int leftOrRight) {
        //从屏幕上方进入
        float startX = getRandomValue(mMinStartX, mMidStartX);
        switch (leftOrRight) {
            case 1:
                startX = getRandomValue(mMinStartX, mMidStartX);
                break;
            case 2:
                startX = getRandomValue(mMidStartX, mMaxStartX);
                break;
        }
        float startY = getRandomValue(mMinStartY, mMaxStartY);
        float vx = getRandomValue(mMinVx, mMaxVx);
        float vy = getRandomValue(mMinVy, mMaxVy);
        FloatParams params = new FloatParams(startX, startY, vx, vy, getRandomValue(mMinAccelerator, mMaxAccelerator));
        ImageView view = new ImageView(getContext());
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.heart_eyes));
        startAnim(view, params);
    }

    /**
     * 开始动画
     *
     * @param view
     * @param params
     */
    private void startAnim(final View view, final FloatParams params) {
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(view, lp);
        final ValueAnimator valueAnimator = new ValueAnimator();
        mAnimatorList.add(valueAnimator);
        valueAnimator.setDuration(ANIM_DURATION * 1000);
        valueAnimator.setObjectValues(new PointF(params.mStartX, params.mStartY));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                PointF point = new PointF();
                float t = fraction * ANIM_DURATION;
                point.x = params.mStartX;
                point.y = params.mStartY + params.mVy * t + 0.5f * params.mAccelerator * t;
                return point;
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mIsShowing) {
                    mAnimatorList.remove(valueAnimator);
                    removeView(view);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                view.setX(point.x);
                view.setY(-point.y);
            }
        });
    }


    /**
     * Float参数
     */
    private class FloatParams {
        /**
         * 初始的x值
         */
        public float mStartX;
        /**
         * 初始的y值
         */
        public float mStartY;
        /**
         * x轴速度
         */
        public float mVx;
        /**
         * y轴速度
         */
        public float mVy;
        /**
         * 加速度
         */
        public float mAccelerator;

        public FloatParams(float startX, float startY, float vx, float vy,
                           float accelerator) {
            mStartX = startX;
            mStartY = startY;
            mVx = vx;
            mVy = vy;
            mAccelerator = accelerator;
        }
    }

    /**
     * 生成随机数
     */
    private float getRandomValue(float min, float max) {
        return min + (float) (Math.random() * (max - min + 1));
    }
}
