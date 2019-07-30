package teotw.com.mywidgets.activities;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;
import android.widget.TextView;

public class CreateViewInSubThreadActivity extends FragmentActivity {
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 主线程创建子线程修改
         * 报错：不能在其他线程刷新view
         */
//        textView = new TextView(this);
//        setContentView(textView);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText("必须在当前线程刷新view");
//            }
//        }).start();
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
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    TextView textView = new TextView(CreateViewInSubThreadActivity.this);
                    WindowManager windowManager = getWindowManager();
                    textView.setText("textview create in sub thread");
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(200, 200, 200, 200, WindowManager.LayoutParams.FIRST_SUB_WINDOW, WindowManager.LayoutParams.TYPE_TOAST, PixelFormat.OPAQUE);
                    windowManager.addView(textView, layoutParams);

                    Looper.loop();
                }
            }).start();
        }
    }
}
