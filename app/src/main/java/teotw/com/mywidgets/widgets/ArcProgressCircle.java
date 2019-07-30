package teotw.com.mywidgets.widgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

import teotw.com.mywidgets.R;

/**
 * Created by MajinBuu on 2018/1/26 0026.
 *
 * @overView 自定义进度圈
 */

public class ArcProgressCircle extends ProgressBar {

    private final float OUTSIDER_CIRCLE_DISTANCE = dipToPx(40);
    private final int   DEGREE_PROGRESS_DISTANCE = dipToPx(55);
    private final int   INSIDER_CIRCLE_DISTANCE  = dipToPx(70);
    private final int   TEXT_DISTANCE            = dipToPx(10);
    private Paint mProgressPaintBg;
    private float mProgressCircleRadius;
    private float mProgressCircleWidth;
    private RectF mProgressRect;
    private float mStartAngle     = 135;
    private float mCircleEndAngle = 270;
    private float mEndAngle       = 405;
    private int   mProgressCircleAngleDistance;
    private int   mProgressCircleColor;
    private int   mProgressColor;
    private Paint mProgressPaint;
    private float mSweepAngle = 4.6f;
    private Paint mSidePaint;
    private int   mCenterX;
    private int   mCenterY;
    private float mOutsideRadius;
    private RectF mOutsideCircleRect;
    private RectF mInsideCircleRect;
    private float mMarkLine;
    private Paint mMarkLinePaint;
    private Paint mCenterContentPaint;
    private float mCenterContentSize;
    private Paint mUnitPaint;
    private Paint mDescPaint;
    private String mCenterUnitString = "";
    private String mCenterDescString;
    private float  mCenterUnitSize;
    private float  mCenterDescSize;
    private float  mMarkLineRadius;
    private Paint  mOutsideMarkPaint;
    private float  mOutsideMarkSize;
    private Paint  mNewPaint;
    private String mCenterContent = "";
    private int      mCenterContentColor;
    private int      mCenterDescColor;
//    private Typeface mFont;
    private int      mOutsideMarkColor;

    public ArcProgressCircle(Context context) {
        this(context, null);
        setWillNotDraw(false);
    }

    public ArcProgressCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setWillNotDraw(false);
    }

    public ArcProgressCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

//        mFont = Typeface.createFromAsset(context.getAssets(), "fonts/PingFangLight.ttf");

        initParm(context, attrs);
        initPaint();

    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("onDraw", "onMeasure");

        initView();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("onDraw", "onLayout");
    }

    private void initView() {
        mProgressRect = new RectF();
        mProgressRect.left = DEGREE_PROGRESS_DISTANCE;
        mProgressRect.top = DEGREE_PROGRESS_DISTANCE;
        mProgressRect.right = this.getMeasuredWidth() - DEGREE_PROGRESS_DISTANCE;
        mProgressRect.bottom = this.getMeasuredWidth() - DEGREE_PROGRESS_DISTANCE;

        mOutsideCircleRect = new RectF();
        mOutsideCircleRect.left = OUTSIDER_CIRCLE_DISTANCE;
        mOutsideCircleRect.top = OUTSIDER_CIRCLE_DISTANCE;
        mOutsideCircleRect.right = this.getMeasuredWidth() - OUTSIDER_CIRCLE_DISTANCE;
        mOutsideCircleRect.bottom = this.getMeasuredWidth() - OUTSIDER_CIRCLE_DISTANCE;

        mInsideCircleRect = new RectF();
        mInsideCircleRect.left = INSIDER_CIRCLE_DISTANCE;
        mInsideCircleRect.top = INSIDER_CIRCLE_DISTANCE;
        mInsideCircleRect.right = this.getMeasuredWidth() - INSIDER_CIRCLE_DISTANCE;
        mInsideCircleRect.bottom = this.getMeasuredWidth() - INSIDER_CIRCLE_DISTANCE;

        mCenterX = this.getMeasuredWidth() / 2;
        mCenterY = this.getMeasuredWidth() / 2;

        setProgressGradient();
    }

    private void setProgressGradient() {
        SweepGradient sweepGradient = new SweepGradient(mCenterX, mCenterY, colors, null);
        mProgressPaint.setShader(sweepGradient);
        Matrix localM = new Matrix();
        localM.setRotate(135, mCenterX, mCenterY);
        sweepGradient.setLocalMatrix(localM);
    }

    private int[] colors = new int[]{Color.parseColor("#00D919"),//绿
            Color.parseColor("#F4CD16"),//绿黄
            Color.parseColor("#F49D02"),//黄
            Color.parseColor("#F16C01"),//橙黄
            Color.parseColor("#D33A05"),//橙
            Color.parseColor("#9E0101"),//红
            Color.parseColor("#9E0101"),//红
            Color.parseColor("#9E0101")//红
    };

    private void initPaint() {

        mProgressPaintBg = new Paint();
        mProgressPaintBg.setColor(mProgressCircleColor);
        mProgressPaintBg.setAntiAlias(true);
        mProgressPaintBg.setStyle(Paint.Style.STROKE);
        mProgressPaintBg.setStrokeCap(Paint.Cap.BUTT);
        mProgressPaintBg.setStrokeWidth(mProgressCircleWidth);

        mProgressPaint = new Paint();
        //        mProgressPaint.setColor(mProgressColor);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeCap(Paint.Cap.BUTT);
        mProgressPaint.setStrokeWidth(mProgressCircleWidth);
        //        float[] positions = {0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.1f};


        mSidePaint = new Paint();
        mSidePaint.setColor(mProgressColor);
        mSidePaint.setAntiAlias(true);
        mSidePaint.setStyle(Paint.Style.STROKE);
        mSidePaint.setStrokeCap(Paint.Cap.ROUND);
        mSidePaint.setStrokeWidth(dipToPx(1));

        mMarkLinePaint = new Paint();
        mMarkLinePaint.setColor(mProgressColor);
        mMarkLinePaint.setAntiAlias(true);
        mMarkLinePaint.setStyle(Paint.Style.STROKE);
        mMarkLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mMarkLinePaint.setStrokeWidth(dipToPx(1));

        mCenterContentPaint = new Paint();
        mCenterContentPaint.setAntiAlias(true);
        mCenterContentPaint.setTextSize(mCenterContentSize);
        mCenterContentPaint.setColor(mCenterContentColor);
        mCenterContentPaint.setTextAlign(Paint.Align.CENTER);
//        mCenterContentPaint.setTypeface(mFont);

        mUnitPaint = new Paint();
        mUnitPaint.setAntiAlias(true);
        mUnitPaint.setTextSize(mCenterUnitSize);
        mUnitPaint.setColor(mCenterContentColor);
        mUnitPaint.setTextAlign(Paint.Align.CENTER);

        mDescPaint = new Paint();
        mDescPaint.setAntiAlias(true);
        mDescPaint.setTextSize(mCenterDescSize);
        mDescPaint.setColor(mCenterDescColor);
        mDescPaint.setTextAlign(Paint.Align.CENTER);

        mOutsideMarkPaint = new Paint();
        mOutsideMarkPaint.setAntiAlias(true);
        mOutsideMarkPaint.setTextSize(mOutsideMarkSize);
        mOutsideMarkPaint.setColor(mOutsideMarkColor);
        mOutsideMarkPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initParm(Context context, AttributeSet attrs) {
        Log.d("onDraw", "initParm");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.acrprogress_circle);
        mOutsideRadius = typedArray.getDimension(R.styleable.acrprogress_circle_outside_circle_radius, 0);
        mMarkLineRadius = typedArray.getDimension(R.styleable.acrprogress_circle_outside_circle_markline_radius, 0);
        mProgressCircleRadius = typedArray.getDimension(R.styleable.acrprogress_circle_progress_circle_radius, 0);
        mProgressCircleWidth = typedArray.getDimension(R.styleable.acrprogress_circle_progress_circle_width, 0);
        typedArray.getDimension(R.styleable.acrprogress_circle_inside_circle_radius, 0);
        mMarkLine = typedArray.getDimension(R.styleable.acrprogress_circle_inside_circle_markline, 0);
        mCenterContentSize = typedArray.getDimension(R.styleable.acrprogress_circle_center_content_size, 0);
        mCenterUnitSize = typedArray.getDimension(R.styleable.acrprogress_circle_center_unit_size, 0);
        mCenterDescSize = typedArray.getDimension(R.styleable.acrprogress_circle_center_desc_size, 0);
        mOutsideMarkSize = typedArray.getDimension(R.styleable.acrprogress_circle_outside_mark_size, 0);

        typedArray.getInteger(R.styleable.acrprogress_circle_center_content_progress, 0);
        mProgressCircleAngleDistance = typedArray.getInteger(R.styleable.acrprogress_circle_progress_circle_distance_angle, 10);

        mCenterUnitString = typedArray.getString(R.styleable.acrprogress_circle_center_unit);
        mCenterDescString = typedArray.getString(R.styleable.acrprogress_circle_center_desc);

        mCenterContentColor = typedArray.getColor(R.styleable.acrprogress_circle_center_content_color, Color.WHITE);
        typedArray.getColor(R.styleable.acrprogress_circle_center_unit_color, Color.WHITE);
        mCenterDescColor = typedArray.getColor(R.styleable.acrprogress_circle_center_desc_color, Color.WHITE);
        mOutsideMarkColor = typedArray.getColor(R.styleable.acrprogress_circle_outside_mark_color, Color.WHITE);
        mProgressCircleColor = typedArray.getColor(R.styleable.acrprogress_circle_progress_circle_color, Color.WHITE);
        mProgressColor = typedArray.getColor(R.styleable.acrprogress_circle_progress_color, Color.WHITE);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        Log.d("onDraw", "onDraw");
        super.onDraw(canvas);

        float tempStartAngle = mStartAngle;
        //画进度背景
        tempStartAngle = drawProgressBg(canvas, tempStartAngle);
        //画进度
        drawProgress(canvas, tempStartAngle);
        //画外圆弧
        drawSideCircle(canvas, mOutsideCircleRect);
        //画内圆弧
        drawSideCircle(canvas, mInsideCircleRect);
        //画内圆刻度线
        drawMarkLine(canvas);
        //画中心进度,单位,描述
        canvas.drawText(mCenterContent, mCenterX, mCenterY + getVerticalOffset(mCenterContentPaint), mCenterContentPaint);
        canvas.drawText(mCenterUnitString, mCenterX, mCenterY + getVerticalOffset(mUnitPaint) + mCenterContentPaint.getTextSize() / 2 + TEXT_DISTANCE * 2.5f, mUnitPaint);
        canvas.drawText(mCenterDescString, mCenterX, mCenterY + getVerticalOffset(mDescPaint) + mCenterContentPaint.getTextSize() / 2 + mUnitPaint.getTextSize() / 2 + TEXT_DISTANCE * 4f, mDescPaint);
        //画外圆标记
        drawOutsideMark(canvas);
    }

    private void drawOutsideMark(Canvas canvas) {
        float offDegree = 360 / 40;
        for (int i = 0; i < 45; i++) {
            /*if (i > 15 && i < 25) {
                //                canvas.rotate(9, mCenterX, mCenterY);
                continue;
            }*/
            if (i == 0) {
                float markLineX = mCenterX + (float) (mMarkLineRadius * Math.sin(-Math.PI / 4 - (i * offDegree * 11 / 14f))) - dipToPx(18);
                float markLineY = mCenterY + (float) (mMarkLineRadius * Math.cos(-Math.PI / 4 - (i * offDegree * 11 / 14f))) - dipToPx(24);
                canvas.drawText("0", markLineX, markLineY, mOutsideMarkPaint);
                canvas.drawText("健康", markLineX, markLineY + getVerticalOffset(mOutsideMarkPaint) + mOutsideMarkPaint.getTextSize() / 2 + TEXT_DISTANCE / 2, mOutsideMarkPaint);
            } else if (i == 5) {
                float markLineX = mCenterX + (float) (mMarkLineRadius * Math.sin(Math.PI / 4 + (i * offDegree * 11 / 14f)));
                float markLineY = mCenterY + (float) (mMarkLineRadius * Math.cos(-Math.PI / 4 - (i * offDegree * 11 / 14f))) - dipToPx(2);
                canvas.drawText("50", markLineX, markLineY, mOutsideMarkPaint);
                canvas.drawText("优", markLineX, markLineY + getVerticalOffset(mOutsideMarkPaint) + mOutsideMarkPaint.getTextSize() / 2 + TEXT_DISTANCE / 2, mOutsideMarkPaint);
            } else if (i == 10) {
                float markLineX = mCenterX + (float) (mMarkLineRadius * Math.sin(-Math.PI / 4 - (i * offDegree * 11 / 14f)));
                float markLineY = mCenterY + (float) (mMarkLineRadius * Math.cos(-Math.PI / 4 - (i * offDegree * 11 / 14f))) - dipToPx(8);
                canvas.drawText("100", markLineX, markLineY, mOutsideMarkPaint);
                canvas.drawText("良", markLineX, markLineY + getVerticalOffset(mOutsideMarkPaint) + mOutsideMarkPaint.getTextSize() / 2 + TEXT_DISTANCE / 2, mOutsideMarkPaint);
            } else if (i == 15) {
                float markLineX = mCenterX + (float) (mMarkLineRadius * Math.sin(-Math.PI / 4 - (i * offDegree * 11 / 14f))) + dipToPx(5);
                float markLineY = mCenterY + (float) (mMarkLineRadius * Math.cos(-Math.PI / 4 - (i * offDegree * 11 / 14f)) * (-1)) - dipToPx(8);
                canvas.drawText("150", markLineX, markLineY, mOutsideMarkPaint);
                canvas.drawText("轻度", markLineX, markLineY + getVerticalOffset(mOutsideMarkPaint) + mOutsideMarkPaint.getTextSize() / 2 + TEXT_DISTANCE / 2, mOutsideMarkPaint);
            } else if (i == 20) {
                float markLineX = mCenterX + (float) (mMarkLineRadius * Math.sin(-Math.PI / 4 - (i * offDegree * 11 / 14f)));
                float markLineY = mCenterY + (float) (mMarkLineRadius * Math.cos(-Math.PI / 4 - (i * offDegree * 11 / 14f))) - dipToPx(12);
                canvas.drawText("200", markLineX, markLineY, mOutsideMarkPaint);
                canvas.drawText("中度", markLineX, markLineY + getVerticalOffset(mOutsideMarkPaint) + mOutsideMarkPaint.getTextSize() / 2 + TEXT_DISTANCE / 2, mOutsideMarkPaint);
            } else if (i == 25) {
                float markLineX = mCenterX + (float) (mMarkLineRadius * Math.sin(Math.PI * 3 / 4 - (i * offDegree * 11 / 14f))) + dipToPx(4);
                float markLineY = mCenterY + (float) (mMarkLineRadius * Math.cos(Math.PI * 3 / 4 - (i * offDegree * 11 / 14f))) - dipToPx(10);
                canvas.drawText("300", markLineX, markLineY, mOutsideMarkPaint);
                canvas.drawText("重度", markLineX, markLineY + getVerticalOffset(mOutsideMarkPaint) + mOutsideMarkPaint.getTextSize() / 2 + TEXT_DISTANCE / 2, mOutsideMarkPaint);
            } else if (i == 30) {
                float markLineX = mCenterX + (float) (mMarkLineRadius * Math.sin(-Math.PI / 4 - (i * offDegree * 11 / 14f))) + dipToPx(32);
                float markLineY = mCenterY + (float) (mMarkLineRadius * Math.cos(-Math.PI / 4 - (i * offDegree * 11 / 14f))) - dipToPx(32);
                canvas.drawText("500", markLineX, markLineY, mOutsideMarkPaint);
                canvas.drawText("严重", markLineX, markLineY + getVerticalOffset(mOutsideMarkPaint) + mOutsideMarkPaint.getTextSize() / 2 + TEXT_DISTANCE / 2, mOutsideMarkPaint);
            }
        }
    }

    private float getVerticalOffset(Paint centerContentPaint) {
        float height = centerContentPaint.descent() - centerContentPaint.ascent();
        return height / 2 - centerContentPaint.descent();
    }

    private void drawMarkLine(Canvas canvas) {
        for (int i = 0; i < 40; i++) {
            if (i > 15 && i < 25) {
                canvas.rotate(9, mCenterX, mCenterY);
                continue;
            }
            if (i % 5 == 0) {
                canvas.drawLine(mCenterX, mCenterY - mInsideCircleRect.height() / 2,
                        mCenterX, mCenterY - mInsideCircleRect.height() / 2 + mMarkLine, mMarkLinePaint);
            }
            canvas.rotate(9, mCenterX, mCenterY);
        }
    }

    private void drawSideCircle(Canvas canvas, RectF rectF) {
        canvas.drawArc(rectF, mStartAngle, mCircleEndAngle, false, mSidePaint);
    }

    public void setProgress(int progress) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1000).setFloatValues(0, progress * 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                ArcProgressCircle.super.setProgress((int) (value / 100));
            }
        });
        valueAnimator.start();
    }

    public void setCenterContent(String value) {
        this.mCenterContent = value;
    }

    public void setCenterUnit(String unit) {
        this.mCenterUnitString = unit;
    }

    private void drawProgress(Canvas canvas, float tempStartAngle) {
        float endAngle = (mEndAngle - mStartAngle) * getProgress() / 100.f + mStartAngle;
        while (tempStartAngle < endAngle) {
            if (tempStartAngle + mSweepAngle > endAngle) {
                canvas.drawArc(mProgressRect, tempStartAngle, endAngle - tempStartAngle, false, mProgressPaint);
            } else {
                canvas.drawArc(mProgressRect, tempStartAngle, 3, false, mProgressPaint);
            }
            tempStartAngle += mSweepAngle;
        }
    }

    private float drawProgressBg(Canvas canvas, float tempStartAngle) {
        while (tempStartAngle < mEndAngle) {
            if (tempStartAngle + mSweepAngle > mEndAngle) {
                canvas.drawArc(mProgressRect, tempStartAngle, mEndAngle - tempStartAngle, false, mProgressPaintBg);
            } else {
                canvas.drawArc(mProgressRect, tempStartAngle, 3, false, mProgressPaintBg);
            }
            tempStartAngle += mSweepAngle;
        }
        tempStartAngle = mStartAngle;
        return tempStartAngle;
    }

    /**
     * dip 转换成px
     *
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
