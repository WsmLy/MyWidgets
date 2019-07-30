package teotw.com.mywidgets.activities;

import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CreateViewInSubThreadActivity extends FragmentActivity {
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 主线程创建子线程修改
         * 报错：不能在其他线程刷新view,但是在oncreate方法中不会报错，因为此时viewRoot还没有创建没法执行检查,所以，onCreate,onStart,onResume都不会报错
         */
        textView = new TextView(this);
        setContentView(textView);
        textView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("返回");
        textView.setPadding(50,50,50,50);
        textView.setBackgroundColor(0xff555555);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText("必须在当前线程刷新view");
//            }
//        }).start();
        /**这样就会报错了**/
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        CreateViewInSubThreadActivity.this.textView.setText("must update ui in mainthread");
//                    }
//                }).start();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 子线程创建子子线程修改，
         * 报错：没有找到window
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                TextView textView = new TextView(CreateViewInSubThreadActivity.this);
//                WindowManager windowManager = getWindowManager();
//                textView.setText("textview create in sub thread");
//                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(200, 200, 200, 200, WindowManager.LayoutParams.FIRST_SUB_WINDOW, WindowManager.LayoutParams.TYPE_TOAST, PixelFormat.OPAQUE);
//                windowManager.addView(textView, layoutParams);
//
//                Looper.loop();
//            }
//        }).start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            /**
             * window创建绑定完成之后，在进行添加view
             * bug：在子线程中创建view会导致返回键失效
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    TextView textView = new TextView(CreateViewInSubThreadActivity.this);
                    WindowManager windowManager = getWindowManager();
                    textView.setText("textview create in sub thread");
                    textView.setFocusable(false);
                    textView.setFocusableInTouchMode(false);
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(200, 200, 200, 200, WindowManager.LayoutParams.FIRST_SUB_WINDOW, WindowManager.LayoutParams.TYPE_TOAST, PixelFormat.OPAQUE);
                    windowManager.addView(textView, layoutParams);

                    Looper.loop();

                }
            }).start();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
