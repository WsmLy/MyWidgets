package teotw.com.mywidgets.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import teotw.com.mywidgets.R;
import teotw.com.mywidgets.widgets.UpDownTextView;

/**
 * @author sam on 2019/4/30
 * 文件描述：自动向上滚动的控件，放淘宝首页
 */
public class UpDownTextViewActivity extends AppCompatActivity {
    private UpDownTextView upDownTextView;

    private int index = 0;//当前显示的item的序列
    private int time = 0;//滚动次数
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                        if (time % 2 == 1) {
//                  index是全局的，判断小于list长度，防止数组越界异常
                            if (index < getList().size() && getList().size() != 0) {
                                index++;
                                if (index == getList().size()) {
                                    index = 0;
                                }
                                upDownTextView.setText(getList().get(index));
//                  自己调自己
                                handler.sendEmptyMessageDelayed(1, 5000);
                            }
                        } else {
                            upDownTextView.startEndAnimation();
                            handler.sendEmptyMessageDelayed(1, 400);
//                    upDownTextView.startStartAnimation();
                        }
                        time++;
                    break;
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updowntextview);

        upDownTextView = findViewById(R.id.updown);
        upDownTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpDownTextViewActivity.this, getList().get(index), Toast.LENGTH_SHORT);
            }
        });
        handler.sendEmptyMessage(1);
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i ++) {
            list.add("item" + i);
        }
        return list;
    }
}
