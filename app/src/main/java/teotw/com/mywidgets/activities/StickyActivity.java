package teotw.com.mywidgets.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import teotw.com.mywidgets.R;
import teotw.com.mywidgets.widgets.StickyLayout;

public class StickyActivity extends AppCompatActivity implements StickyLayout.OnGiveUpTouchEventListener {
    private StickyLayout mStickLayout;
    private boolean mIsTop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);

        mStickLayout = findViewById(R.id.sticky);
        mStickLayout.setOnGiveUpTouchEventListener(this);
        mStickLayout.setSticky(true);
        mStickLayout.setHeightChangeListener(new StickyLayout.HeightChangeListener() {
            @Override
            public void notifyChange(float scale) {
                setHeadContentSize(scale);
            }
        });
        mStickLayout.setScroll(new StickyLayout.IpmlScrollChangListener() {
            @Override
            public boolean isReady2Pull() {
                //                return isOnTop(mRecyclerView);
                return mIsTop;
            }

            @Override
            public void isMoving() {
                /*//监控主页面是否在顶部的状态
                int status = mStickLayout.getStatus();
                if (status == 1) {
                    mFlHomeBottom.setVisibility(View.VISIBLE);
                } else {
                    mFlHomeBottom.setVisibility(View.GONE);
                }*/
            }
        });
    }

    /**
     * head缩放动画
     */
    private void setHeadContentSize(float scale) {
//        if (mHeadContent.getMeasuredHeight() < mWaveHeight) {
//            mHeadContent.setScaleX(scale);
//            mHeadContent.setScaleY(scale);
//        }
    }

    @Override
    public boolean giveUpTouchEvent(MotionEvent event) {
        return false;
    }
}
