package teotw.com.mywidgets;

import android.content.Context;
import android.util.TypedValue;

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2018/11/15
 * 描    述：基本工具类
 * 修订历史：
 * ================================================
 */

public class Utils {
    /**
     * dp转换成px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //px转dp
    public static int px2dp(Context context, int pxValue) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics()));
    }

    /**
     * sp转换成px
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
