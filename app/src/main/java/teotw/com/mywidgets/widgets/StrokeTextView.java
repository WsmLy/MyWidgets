package teotw.com.mywidgets.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.example.myutils.utils.DisplayUtil;

import teotw.com.mywidgets.R;

/**
 * @author wsm on 2019/3/29
 * 文件描述：描边TextView
 */
public class StrokeTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint strokePaint;
    public StrokeTextView(Context context) {
        super(context);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // lazy load
        if (strokePaint == null) {
            strokePaint = new TextPaint();
        }
        // 复制原来TextViewg画笔中的一些参数
        TextPaint paint = getPaint();
        strokePaint.setTextSize(paint.getTextSize());
        strokePaint.setTypeface(paint.getTypeface());
        strokePaint.setFlags(paint.getFlags());
        strokePaint.setAlpha(paint.getAlpha());

        // 自定义描边效果
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(getResources().getColor(R.color.white));
        strokePaint.setStrokeWidth(DisplayUtil.dp2px(1f));

        String text = getText().toString();
        //在文本底层画出带描边的文本
        canvas.drawText(text, (getWidth() - strokePaint.measureText(text)) / 2,
                getBaseline(), strokePaint);
        super.onDraw(canvas);
    }
}
