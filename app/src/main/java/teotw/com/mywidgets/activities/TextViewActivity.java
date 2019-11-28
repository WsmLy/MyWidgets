package teotw.com.mywidgets.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.myutils.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import teotw.com.mywidgets.R;
import teotw.com.mywidgets.widgets.manytextview.ComponTextLayout;
import teotw.com.mywidgets.widgets.manytextview.ExpandableTextView;
import teotw.com.mywidgets.widgets.manytextview.UpDownTextView;

import static teotw.com.mywidgets.Utils.dp2px;

/**
 * @author sam on 2019/4/30
 * 文件描述：自动向上滚动的控件，放淘宝首页
 */
public class TextViewActivity extends AppCompatActivity {
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
                Toast.makeText(TextViewActivity.this, getList().get(index), Toast.LENGTH_SHORT);
            }
        });
        handler.sendEmptyMessage(1);

        ComponTextLayout componTextLayout = findViewById(R.id.compose_text);
        componTextLayout.setText("kjdhfasljklgjkladrjfjkladsjflkadsjglgkljadfjskgkljdkla", "sjaafijaklsjf", "sdjffijas");

        ExpandableTextView expandableTextView = findViewById(R.id.expanded_text);
        int viewWidth = DisplayUtil.getDisplayMetrics(this).widthPixels - dp2px(this, 20f);
        expandableTextView.initWidth(viewWidth);
        expandableTextView.setMaxLines(3);
        expandableTextView.setHasAnimation(true);
        expandableTextView.setCloseInNewLine(true);
        expandableTextView.setOpenSuffixColor(getResources().getColor(R.color.colorAccent));
        expandableTextView.setCloseSuffixColor(getResources().getColor(R.color.colorAccent));
        expandableTextView.setOriginalText("在全球，随着Flutter被越来越多的知名公司应用在自己的商业APP中，" +
                "Flutter这门新技术也逐渐进入了移动开发者的视野，尤其是当Google在2018年IO大会上发布了第一个" +
                "Preview版本后，国内刮起来一股学习Flutter的热潮。\n\n为了更好的方便帮助中国开发者了解这门新技术" +
                "，我们，Flutter中文网，前后发起了Flutter翻译计划、Flutter开源计划，前者主要的任务是翻译" +
                "Flutter官方文档，后者则主要是开发一些常用的包来丰富Flutter生态，帮助开发者提高开发效率。而时" +
                "至今日，这两件事取得的效果还都不错！"
        );
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i ++) {
            list.add("item" + i);
        }
        return list;
    }
}
