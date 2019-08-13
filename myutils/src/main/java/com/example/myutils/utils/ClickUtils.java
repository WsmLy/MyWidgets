package com.example.myutils.utils;

import com.example.myutils.interfaces.BeforExitToDoListener;

/**
 * create by sam on 2019-08-02
 * description: some utils about click
 */
public class ClickUtils {
    private static String TAG = "ClickUtils";
    private static long lastClickTime;

    private static int clickTimes = 1;

    /**
     * is continuous click
     *
     * @param timeInternal define how long time click again is continuous click, its unit is ms which is 1000 * second
     * @return
     */
    public static boolean isFastDoubleClick(long timeInternal) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        LogUtils.d(TAG, new StringBuilder().append("time:").append(time).append("\t\t\tlastClickTime:").append(lastClickTime).append("\t\t\ttimeD:").append(timeD).append("\t\t\ttimeInternal:").append(timeInternal).toString());
        lastClickTime = time;
        if (0 < timeD && timeD < timeInternal) {
            clickTimes ++;
            ToastUtil.showToastForShort(new StringBuilder().append("快速点击").append(clickTimes).append("次！").toString());
            return true;
        }
        clickTimes = 1;
        return false;
    }

    /**
     * exit this application after twice continuous click return key
     * you should using this method in onClick() method
     * if you want doing something when the application exit, you can add a listener as a parameter into the method,and put these things into the exit method of this listener
     * you should use it in FirstActivity to ensure all activity is finished
     */
    public static void doubleClickToExit() {
        doubleClickToExit(null);
    }

    public static void doubleClickToExit(BeforExitToDoListener listener) {
        long time = System.currentTimeMillis();
        LogUtils.d(TAG, new StringBuilder().append("time:").append(time).append("\t\t\tlastClickTime:").append(lastClickTime).toString());
        if (time - lastClickTime < 2000) {
            if (listener != null)
                listener.exit();
//            System.exit(0);
        } else {
            ToastUtil.showToastForShort("再次点击退出应用");
        }
        lastClickTime = time;
    }
}
