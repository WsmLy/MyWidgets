package com.example.myutils.utils;

import android.util.DisplayMetrics;

import com.example.myutils.beans.ScreenInfo;

/**
 * create by sam at 2019/07/31
 * descriptions: 跟尺寸相关的工具类，包括各种单位之间的转换和获取屏幕宽高、获取状态栏高度
 * revise at 2019.08.02: 获取屏幕宽高换成返回自定义屏幕信息。
 */
public class DisplayUtil {

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * （DisplayMetrics类中属性density）
     *
     * @return
     */
    public static int px2dp(float pxValue) {
        final float scale = getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * （DisplayMetrics类中属性density）
     *
     * @return
     */
    public static int dp2px(float dipValue) {
        final float scale = getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * （DisplayMetrics类中属性scaledDensity）
     *
     * @return
     */
    public static int px2sp(float pxValue) {
        final float fontScale = getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将dp值转换为sp值，保证文字大小不变
     * （DisplayMetrics类中属性scaledDensity）
     *
     * @return
     */
    public static int dp2sp(float dpValue) {
        return px2dp(dp2px(dpValue));
    }

    /**
     * 将sp值转换为dp值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2dp(float spValue) {
        return px2dp(sp2px(spValue));
    }

    /**
     * 得到屏幕的宽度和高度
     *
     * @return
     */
    public static ScreenInfo getScreenSize() {
        DisplayMetrics dm;
        dm = getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        return new ScreenInfo().setHeight(screenHeight).setWidth(screenWidth);
    }


    /**
     * 得到屏幕的信息DisplayMetrics
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics() {
        return ContextUtils.getContext().getResources().getDisplayMetrics();
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = ContextUtils.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ContextUtils.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
