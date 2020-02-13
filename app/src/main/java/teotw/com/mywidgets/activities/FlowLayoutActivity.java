package teotw.com.mywidgets.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import teotw.com.mywidgets.R;
import teotw.com.mywidgets.adapters.TagAdapter;
import teotw.com.mywidgets.widgets.flowlayout.FlowLayout;
import teotw.com.mywidgets.widgets.tagcloud.TagCloudView;

/**
 * 流式布局（居左、居中、居右）
 */
public class FlowLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout);

        //居左的流式布局
        FlowLayout flowLayoutLeft = findViewById(R.id.flowLayout_left);
        flowLayoutLeft.removeAllViews();
        for (int i = 0; i < 5; i ++) {
            TextView textView = new TextView(this);
            textView.setPadding(20,20,20,20);
            textView.setText("left " + i);
            int width = getResources().getDisplayMetrics().widthPixels / (int) Math.ceil((double) 5/2) - 100;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 20, 20, 20);
            textView.setLayoutParams(params);
            textView.setBackgroundColor(0xff000ff0);
            flowLayoutLeft.addView(textView);
        }

        //居中的流式布局
        FlowLayout flowLayoutCenter = findViewById(R.id.flowLayout_center);
        flowLayoutCenter.removeAllViews();
        for (int i = 0; i < 5; i ++) {
            TextView textView = new TextView(this);
            textView.setPadding(20,20,20,20);
            textView.setText("center " + i);
            int width = getResources().getDisplayMetrics().widthPixels / (int) Math.ceil((double) 5/2) - 100;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 20, 20, 20);
            textView.setLayoutParams(params);
            textView.setBackgroundColor(0xff000ff0);
            flowLayoutCenter.addView(textView);
        }

        //居右的流式布局
        FlowLayout flowLayoutRight = findViewById(R.id.flowLayout_right);
        flowLayoutRight.removeAllViews();
        for (int i = 0; i < 5; i ++) {
            TextView textView = new TextView(this);
            textView.setPadding(20,20,20,20);
            textView.setText("right " + i);
            int width = getResources().getDisplayMetrics().widthPixels / (int) Math.ceil((double) 5/2) - 100;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 20, 20, 20);
            textView.setLayoutParams(params);
            textView.setBackgroundColor(0xff000ff0);
            flowLayoutRight.addView(textView);
        }

    }
}
