package teotw.com.mywidgets.widgets.manytextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import teotw.com.mywidgets.R;

/**
 * created by samwsm at 2019-11-27 23:30
 * update by samwsm at 2019-11-27 23:30
 * updateDetail :
 */
public class MiddlePointTextView extends android.support.v7.widget.AppCompatTextView {
    private String startString, middleString, endString;
    private float startSize, middleSize, endSize;
    private int startColor, middleColor, endColor;
    private Context context;
    private Paint paintStart = new Paint();
    private Paint paintMiddle = new Paint();
    private Paint paintEnd = new Paint();
    public MiddlePointTextView(Context context) {
        this(context, null);
    }

    public MiddlePointTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MiddlePointTextView);
        startString = array.getString(R.styleable.MiddlePointTextView_text_start);
        middleString = array.getString(R.styleable.MiddlePointTextView_text_middle);
        endString = array.getString(R.styleable.MiddlePointTextView_text_end);
        startSize = array.getDimension(R.styleable.MiddlePointTextView_text_start_size, 50);
        middleSize = array.getDimension(R.styleable.MiddlePointTextView_text_middle_size, 50);
        endSize = array.getDimension(R.styleable.MiddlePointTextView_text_end_size, 50);
        startColor = array.getColor(R.styleable.MiddlePointTextView_text_start_color, 0xff999999);
        middleColor = array.getColor(R.styleable.MiddlePointTextView_text_middle_color, 0xff999999);
        endColor = array.getColor(R.styleable.MiddlePointTextView_text_end_color, 0xff999999);
        array.recycle();
    }

    public MiddlePointTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
    }

    @Override
    public void setEllipsize(TextUtils.TruncateAt where) {
        super.setEllipsize(where);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintEnd.setTextSize(endSize);
        paintEnd.setColor(endColor);
        paintMiddle.setTextSize(middleSize);
        paintMiddle.setColor(middleColor);
        paintStart.setTextSize(startSize);
        paintStart.setColor(startColor);
//        paintStart
        float screenWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        float startWidth = paintStart.measureText(startString);
        float endWidth = paintStart.measureText(endString);
        float middleWidth = paintStart.measureText(middleString);
        if (screenWidth >= startWidth + middleWidth + endWidth) {
            canvas.drawText(startString, 0, 50, paintStart);
            canvas.drawText(middleString, startWidth, 50, paintMiddle);
            canvas.drawText(endString, startWidth + middleWidth, 50, paintEnd);
        } else {
            StringBuffer paintStartFinal = new StringBuffer();
            float startMaxWidth = paintStart.measureText("...");
            for (int i = 0; i < startString.length(); i ++) {
                if (startMaxWidth < screenWidth - endWidth - middleWidth - paintStart.measureText(String.valueOf(startString.charAt(i)))) {
                    startMaxWidth += paintStart.measureText(String.valueOf(startString.charAt(i)));
                    paintStartFinal.append(startString.charAt(i));
                } else {
                    break;
                }
            }
            paintStartFinal.append("...");
            canvas.drawText(endString, screenWidth - endWidth, 50, paintEnd);
            canvas.drawText(middleString, screenWidth - endWidth - middleWidth, 50, paintMiddle);
            canvas.drawText(paintStartFinal.toString(), 0, 50, paintStart);
        }
    }

    public void setText(String textStart, String textMiddle, String textEnd) {
        startString = textStart;
        middleString = textMiddle;
        endString = textEnd;
        invalidate();
    }
}
