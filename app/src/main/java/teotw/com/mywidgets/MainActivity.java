package teotw.com.mywidgets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import teotw.com.mywidgets.activities.ArcProgressActivity;
import teotw.com.mywidgets.activities.CreateViewInSubThreadActivity;
import teotw.com.mywidgets.activities.DoubleRecyclerActivity;
import teotw.com.mywidgets.activities.FlowLayoutActivity;
import teotw.com.mywidgets.activities.ImageVideoActivity;
import teotw.com.mywidgets.activities.ShineButtonActivity;
import teotw.com.mywidgets.activities.StickyActivity;
import teotw.com.mywidgets.activities.TagActivity;
import teotw.com.mywidgets.activities.TextViewActivity;
import teotw.com.mywidgets.widgets.flowlayout.FlowLayout;

/**
 * create by sam in 2019/4/30  由刘毅创建于2019年4月30日
 * to show how to use wigets which created by sam  展示如何使用我自定义的组件
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.up_down_textview).setOnClickListener(this);//自动向上滚动的控件
        findViewById(R.id.create_view_insubthread).setOnClickListener(this);//在子线程创建view
        findViewById(R.id.arc_progress).setOnClickListener(this);//表盘进度条
        findViewById(R.id.sticky).setOnClickListener(this);//sticky
        findViewById(R.id.shine_button).setOnClickListener(this);//shine_button
        findViewById(R.id.tag_cloud).setOnClickListener(this);//tag_cloud
        findViewById(R.id.image_video_banner).setOnClickListener(this);//image_video_banner
        findViewById(R.id.double_recyclerview).setOnClickListener(this);//double_recyclerview
        findViewById(R.id.flow_layout).setOnClickListener(this);//flow_layout
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_down_textview://自动向上滚动的控件
                startActivity(new Intent(MainActivity.this, TextViewActivity.class));
                break;
            case R.id.create_view_insubthread://子线程创建view
                startActivity(new Intent(MainActivity.this, CreateViewInSubThreadActivity.class));
                break;
            case R.id.arc_progress://表盘进度条
                startActivity(new Intent(MainActivity.this, ArcProgressActivity.class));
                break;
            case R.id.sticky://sticky
                startActivity(new Intent(MainActivity.this, StickyActivity.class));
                break;
            case R.id.shine_button://shine_button
                startActivity(new Intent(MainActivity.this, ShineButtonActivity.class));
                break;
            case R.id.tag_cloud://tag_cloud
                startActivity(new Intent(MainActivity.this, TagActivity.class));
                break;
            case R.id.image_video_banner://image_video_banner
                startActivity(new Intent(MainActivity.this, ImageVideoActivity.class));
                break;
            case R.id.double_recyclerview://image_video_banner
                startActivity(new Intent(MainActivity.this, DoubleRecyclerActivity.class));
                break;
            case R.id.flow_layout://flow_layout
                startActivity(new Intent(MainActivity.this, FlowLayoutActivity.class));
                break;
        }
    }
}
