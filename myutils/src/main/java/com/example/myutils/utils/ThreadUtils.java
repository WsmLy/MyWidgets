package com.example.myutils.utils;

/**
 * create by sam on 2019-08-05
 * description: some utils about thread
 */
public class ThreadUtils {
    public static final long DEFAULT_SLEEP_TIME = 500;

    private boolean isRuning = false;

    /**
     * is thread running?
     */
    public boolean isRuning() {
        return isRuning;
    }

    /**
     * when this thread is running, it will sleep after ${defaultSleepTime}/1000 seconds
     */
    public void runWithTime(final long defaultSleepTime) {
        isRuning = true;
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(defaultSleepTime, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isRuning = false;
                super.run();
            }
        }.start();
    }
}
