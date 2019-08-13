package com.example.myutils;

import android.app.Application;

import com.example.myutils.utils.ContextUtils;
import com.example.myutils.utils.LogUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtils.setContext(this);
        LogUtils.setIsDebug(BuildConfig.DEBUG);
    }
}
