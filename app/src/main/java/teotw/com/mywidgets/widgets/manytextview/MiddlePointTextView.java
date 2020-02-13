package teotw.com.mywidgets.widgets.manytextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import teotw.com.mywidgets.R;

/**
 * created by samwsm at 2019-11-27 23:30
 * update by samwsm at 2019-11-27 23:30
 * updateDetail :
 */
public class MiddlePointTextView extends View {
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
        initPaint();
    }

    public MiddlePointTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    private void initPaint() {
        if (startString == null) {
            startString = "";
        }
        if (middleString == null) {
            middleString = "";
        }
        if (endString == null) {
            endString = "";
        }
        paintEnd.setTextSize(endSize);
        paintEnd.setColor(endColor);
        paintMiddle.setTextSize(middleSize);
        paintMiddle.setColor(middleColor);
        paintStart.setTextSize(startSize);
        paintStart.setColor(startColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int minimumWidth = getSuggestedMinimumWidth();
        final int minimumHeight = getSuggestedMinimumHeight();
        int width = measureWidth(minimumWidth, widthMeasureSpec);
        int height = measureHeight();
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int defaultWidth, int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.AT_MOST:
                defaultWidth = (int) paintStart.measureText(startString) + (int) paintMiddle.measureText(middleString) + (int) paintEnd.measureText(endString) + getPaddingLeft() + getPaddingRight();
                break;
            case MeasureSpec.EXACTLY:
                defaultWidth = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                defaultWidth = Math.max(defaultWidth, specSize);
        }
        return defaultWidth;
    }

    private int measureHeight() {
        Paint.FontMetrics fontMetricsStart = paintStart.getFontMetrics();
        Paint.FontMetrics fontMetricsMiddle = paintMiddle.getFontMetrics();
        Paint.FontMetrics fontMetricsEnd = paintEnd.getFontMetrics();

        int defaultHeight = getMaxValue((int) (-fontMetricsStart.top + fontMetricsStart.bottom), (int) (-fontMetricsMiddle.top + fontMetricsMiddle.bottom), (int) (-fontMetricsEnd.top + fontMetricsEnd.bottom)) + getPaddingTop() + getPaddingBottom();
//        1.基准点是baseline
//        2.ascent：是baseline之上至字符最高处的距离
//        3.descent：是baseline之下至字符最低处的距离
//        4.leading：是上一行字符的descent到下一行的ascent之间的距离,也就是相邻行间的空白距离
//        5.top：是指的是最高字符到baseline的值,即ascent的最大值
//        6.bottom：是指最低字符到baseline的值,即descent的最大值
        return defaultHeight;
    }

    private int getMaxValue(int... value) {
        int max = value[0];
        for (int i = 1; i < value.length; i++) {
            int param = value[i];
            max = Math.max(max, param);
        }
        return max;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        paintStart
        float screenWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        float startWidth = paintStart.measureText(startString);
        float endWidth = paintStart.measureText(endString);
        float middleWidth = paintStart.measureText(middleString);
        Paint.FontMetrics fontMetricsStart = paintStart.getFontMetrics();
        Paint.FontMetrics fontMetricsMiddle = paintMiddle.getFontMetrics();
        Paint.FontMetrics fontMetricsEnd = paintEnd.getFontMetrics();
        if (screenWidth >= startWidth + middleWidth + endWidth) {
            canvas.drawText(startString, 0, fontMetricsStart.leading - fontMetricsStart.top, paintStart);
            canvas.drawText(middleString, startWidth, fontMetricsMiddle.leading - fontMetricsMiddle.top, paintMiddle);
            canvas.drawText(endString, startWidth + middleWidth, fontMetricsEnd.leading - fontMetricsEnd.top, paintEnd);
        } else {
            StringBuffer paintStartFinal = new StringBuffer();
            float startMaxWidth = paintStart.measureText("...");
            for (int i = 0; i < startString.length(); i++) {
                if (startMaxWidth < screenWidth - endWidth - middleWidth - paintStart.measureText(String.valueOf(startString.charAt(i)))) {
                    startMaxWidth += paintStart.measureText(String.valueOf(startString.charAt(i)));
                    paintStartFinal.append(startString.charAt(i));
                } else {
                    break;
                }
            }
//            int finalLength = paintStart.breakText(startString, true, screenWidth - endWidth - middleWidth - startMaxWidth, null);
//            startString = startString.substring(0, finalLength);
//            paintStartFinal.append(startString);
            paintStartFinal.append("...");
            canvas.drawText(endString, screenWidth - endWidth, fontMetricsEnd.leading - fontMetricsEnd.top, paintEnd);
            canvas.drawText(middleString, screenWidth - endWidth - middleWidth, fontMetricsMiddle.leading - fontMetricsMiddle.top, paintMiddle);
            canvas.drawText(paintStartFinal.toString(), 0, fontMetricsStart.leading - fontMetricsStart.top, paintStart);
        }
    }

    public void setText(String textStart, String textMiddle, String textEnd) {
        startString = textStart;
        middleString = textMiddle;
        endString = textEnd;
        requestLayout();
        invalidate();
    }
}
