package com.example.myutils.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * create by sam on 2019-08-05
 * description: some utils about network status
 */
public class NetUtils {
    /**
     * is network connected
     * donnot forget add permission ACCESS_NETWORK_STATE
     */
    public boolean isNetConnected() {
        // 创建并初始化连接对象
        ConnectivityManager connMan = (ConnectivityManager) ContextUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // 判断初始化是否成功并作出相应处理
        if (connMan != null) {
            // 调用getActiveNetworkInfo方法创建对象,如果不为空则表明网络连通，否则没连通
            NetworkInfo info = connMan.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
}
