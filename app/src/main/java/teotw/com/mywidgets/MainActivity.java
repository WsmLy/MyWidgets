package teotw.com.mywidgets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import teotw.com.mywidgets.activities.UpDownTextViewActivity;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_down_textview://自动向上滚动的控件
                startActivity(new Intent(MainActivity.this, UpDownTextViewActivity.class));
                break;
        }
    }
}