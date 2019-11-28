package teotw.com.mywidgets.widgets.manytextview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myutils.utils.DisplayUtil;

/**
 * created by samwsm at 2019-11-27 21:52
 * update by samwsm at 2019-11-27 21:52
 * updateDetail :
 */
public class ComponTextLayout extends LinearLayout {
    private Context context;
    private TextView startText;
    private TextView middleText;
    private TextView endText;

    public ComponTextLayout(Context context) {
        super(context);
        init(context);
    }

    public ComponTextLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ComponTextLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        startText = new TextView(context);
        middleText = new TextView(context);
        endText = new TextView(context);
        startText.setTextColor(Color.BLACK);
        middleText.setTextColor(Color.BLACK);
        endText.setTextColor(Color.BLACK);
        addView(startText);
        addView(middleText);
        addView(endText);
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    public void setText(String start, String middle, String end) {
        endText.setText(end);
        middleText.setText(middle);
        startText.setText(start);
        Paint p = endText.getPaint();
        Paint p1 = middleText.getPaint();
        LayoutParams layoutParams = new LayoutParams((int)(DisplayUtil.getDisplayMetrics(context).widthPixels
                - this.getPaddingLeft() - this.getPaddingRight()
                - p.measureText(end) - p1.measureText(middle)), ViewGroup.LayoutParams.MATCH_PARENT);
        startText.setLayoutParams(layoutParams);
        startText.setEllipsize(TextUtils.TruncateAt.END);
        startText.setMaxLines(1);
        middleText.setMaxLines(1);
        endText.setMaxLines(1);
    }
}
