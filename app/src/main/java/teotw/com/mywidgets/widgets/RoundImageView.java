package teotw.com.mywidgets.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import teotw.com.mywidgets.R;

/**
 * created by samwsm at 2019-08-25 08:50
 * update by samwsm at 2019-08-25 08:50
 * updateDetail :
 */
public class RoundImageView extends View {
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;
    //round radius size
    private int mRadius;
    //width & height
    private int mWidth;
    private int mHeight;
    private Bitmap src;

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i ++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.RoundImageView_type:
                    type = array.getInt(attr, 0);
                    break;
                case R.styleable.RoundImageView_borderRadius:
                    int defValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics());
                    mRadius = array.getDimensionPixelSize(attr, defValue);
                    break;
                case R.styleable.RoundImageView_src:
                    src = BitmapFactory.decodeResource(getResources(), array.getResourceId(attr, 0));
            }
        }
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //set width
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            mWidth = specSize;
        } else {
            int desireByImg = getPaddingStart() + getPaddingEnd() + src.getWidth();
            if (specMode == MeasureSpec.AT_MOST) {// wrap_content
                mWidth = Math.min(desireByImg, specSize);
            } else {
                mWidth = desireByImg;
            }
        }

        //set height
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            mHeight = specSize;
        } else {
            int desire = getPaddingTop() + getPaddingBottom() + src.getHeight();
            if (specMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(desire, specSize);
            } else {
                mHeight = desire;
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (type) {
            case TYPE_CIRCLE:
                int min = Math.min(mWidth, mHeight);
                src = Bitmap.createScaledBitmap(src, min, min, false);
                canvas.drawBitmap(createCircleImage(src, min), 0, 0, null);
                break;
            case TYPE_ROUND:
                src = Bitmap.createScaledBitmap(src, mWidth, mHeight, false);
                canvas.drawBitmap(createRoundConerImage(src), 0, 0, null);
                break;
        }
    }
    /**
     * draw round coner
     * @param source
     * @return
     */
    private Bitmap createRoundConerImage(Bitmap source) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * draw circle
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(min/2,min/2,min/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }
}
