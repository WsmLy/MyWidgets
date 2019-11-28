package teotw.com.mywidgets.widgets.manytextview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import teotw.com.mywidgets.Utils;

/**
 * Created by sky on 2017/3/15.
 * 自动向上滚动控件
 */
public class UpDownTextView extends TextView implements Animator.AnimatorListener {

    private static final String TAG = "UpDownTextView";

    private static final int ANIMATION_DURATION = 500;
    private float height;
    private AnimatorSet mAnimatorStartSet;
    private AnimatorSet mAnimatorEndSet;
    private String mText;

    public UpDownTextView(Context context) {
        super(context);
    }

    public UpDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 此方法就是获取view的高度
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getHeight();
    }

    /**
     * --- 初始化向上脱离屏幕的动画效果 ---
     */
    private void initStartAnimation() {
        ObjectAnimator translate = ObjectAnimator.ofFloat(this, "translationY", Utils.dp2px(getContext(), 50), 0);
        mAnimatorStartSet = new AnimatorSet();
        mAnimatorStartSet.play(translate);//.with(alpha);
        mAnimatorStartSet.setDuration(ANIMATION_DURATION);
        mAnimatorStartSet.addListener(this);
    }

    /**
     * --- 初始化从屏幕下面向上的动画效果 ---
     */
    private void initEndAnimation() {
        //translationY 属性肯定要归为0，不然显示的内容就有偏移了；alpha 也是一样
        ObjectAnimator translate = ObjectAnimator.ofFloat(this, "translationY", 0, Utils.dp2px(getContext(), -50));
        mAnimatorEndSet = new AnimatorSet();
        mAnimatorEndSet.play(translate);//.with(alpha);
        mAnimatorEndSet.setDuration(ANIMATION_DURATION);
        mAnimatorEndSet.addListener(this);
    }

    public void startEndAnimation() {
        if (mAnimatorEndSet == null) {
            initEndAnimation();
        }
        mAnimatorEndSet.start();
    }

    public void startStartAnimation() {
        if (mAnimatorStartSet == null) {
            initStartAnimation();
        }
        mAnimatorStartSet.start();
    }

    /**
     * --- 设置内容 ---
     **/
    public void setText(String text) {
        if (TextUtils.isEmpty(text)) {
            Log.e(TAG, "--- 请确保text不为空 ---");
            return;
        }
        mText = text;
        if (null == mAnimatorStartSet) {
            initStartAnimation();
        }
        mAnimatorStartSet.start();
    }


    @Override
    public void onAnimationStart(Animator animator) {
        super.setText(mText);
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        if (null == mAnimatorEndSet) {
            initEndAnimation();
        }
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}