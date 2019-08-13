package com.example.myutils.beans;

/**
 * create by sam at 2019.08.02
 * description: 获取手机屏幕的相关信息
 */
public class ScreenInfo {
    private int width;
    private int height;

    public ScreenInfo setWidth(int width) {
        this.width = width;
        return this;
    }

    public ScreenInfo setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
