package com.zero.floatview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

/**
 * BaseView
 * @author LinZewu
 * @time 16-3-31
 */
public class FlowBaseView extends RelativeLayout {
    
    public static float sDensity = 1.0f;
    public static int sDensityDpi;
    public static float sFontDensity;

    public static float sScaleX = 1f;
    public static float sScaleY = 1f;

    /**
     * 默认设计的大小为720×1080
     */
    public static float sDefaultWidth = 720;
    public static float sDefaultHeight = 1080;
    
    /**
     * 触摸距离
     */
    public static int sTouchSlop;


    public FlowBaseView(Context context) {
        super(context);
        resetDensity(context);
    }
    
    public FlowBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        resetDensity(context);
    }
    

    /**
     * dip/dp转像素
     *
     * @param dipValue dip或 dp大小
     * @return 像素值
     */
    public static int dip2px(float dipValue) {
        return (int) (dipValue * sDensity + 0.5f);
    }

    /**
     * 像素转dip/dp
     *
     * @param pxValue 像素大小
     * @return dip值
     */
    public static int px2dip(float pxValue) {
        final float scale = sDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param spValue sp大小
     * @return 像素值
     */
    public static int sp2px(float spValue) {
        final float scale = sDensity;
        return (int) (scale * spValue);
    }

    /**
     * px转sp
     *
     * @param pxValue 像素大小
     * @return sp值
     */
    public static int px2sp(float pxValue) {
        final float scale = sDensity;
        return (int) (pxValue / scale);
    }
    
    public synchronized static void resetDensity(Context context) {
        if (context != null && null != context.getResources()) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            sDensity = metrics.density;
            sFontDensity = metrics.scaledDensity;
            sDensityDpi = metrics.densityDpi;

            sScaleX = getScreenWidth(context)/sDefaultWidth;
            sScaleY = getScreenHeight(context)/sDefaultHeight;
            try {
                final ViewConfiguration configuration = ViewConfiguration.get(context);
                if (null != configuration) {
                    sTouchSlop = configuration.getScaledTouchSlop();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
}
