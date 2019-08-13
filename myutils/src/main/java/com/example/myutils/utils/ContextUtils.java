package com.example.myutils.utils;

import android.content.Context;

/**
 * create by sam at 2019/07/31
 * descriptions: Context相关的工具，获取context，获取的是application的context
 */
public class ContextUtils {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context c) {
        context = c;
    }
}
