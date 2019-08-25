package teotw.com.mywidgets.widgets.fabshine;

import android.animation.ValueAnimator;
import android.graphics.Canvas;

/**
 * @author Chad
 * @since 16/7/5 下午5:09
 **/
class ShineAnimator extends ValueAnimator {

    ShineAnimator(long duration,float max_value,long delay) {
        setFloatValues(1f, max_value);
        setDuration(duration);
        setStartDelay(delay);
        setInterpolator(new EasingInterpolator(Ease.QUART_OUT));
    }

    void startAnim() {
        start();
    }
}
