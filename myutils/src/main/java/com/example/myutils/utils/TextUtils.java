package com.example.myutils.utils;

/**
 * create by sam on 2019-08-05
 * description: some utils for text
 */
public class TextUtils {
    /**
     * is the text empty?
     */
    public static boolean isEmpty(String text) {
        if (text == null || "".equals(text)) {
            return true;
        } else {
            return false;
        }
    }
}
