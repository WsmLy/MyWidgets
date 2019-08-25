package teotw.com.mywidgets.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import teotw.com.mywidgets.R;

/**
 * created by samwsm at 2019-08-25 12:53
 * update by samwsm at 2019-08-25 12:53
 * updateDetail :
 */
public class RoundImageView1 extends android.support.v7.widget.AppCompatImageView {

    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;
    //图片
    private Bitmap mSrc;
    //圆角大小
    private int mRadius;

    public RoundImageView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义的属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        //获取自定以属性的数目
        int count = a.getIndexCount();
        for (int i=0 ; i<count ; i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.RoundImageView_borderRadius:
                    int defValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10f,getResources().getDisplayMetrics());
                    mRadius = a.getDimensionPixelSize(attr, defValue);
                    break;
                case R.styleable.RoundImageView_type:
                    type = a.getInt(attr,0);
                    break;
            }
        }

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null){
            Bitmap bitmap = getBitmap(getDrawable());
            if (bitmap != null){
                switch (type){
                    case TYPE_CIRCLE:
                        //获取ImageView中的宽高，取最小值
                        int min = Math.min(getMeasuredWidth(),getMeasuredHeight());
                        //从当前存在的Bitmap，按一定的比例创建一个新的Bitmap。
                        mSrc = Bitmap.createScaledBitmap(bitmap, min, min, false);
                        canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);

                        break;
                    case TYPE_ROUND:
                        mSrc = Bitmap.createScaledBitmap(bitmap, getMeasuredWidth(), getMeasuredHeight(), false);
                        canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0, null);

                        break;
                }
            }
        }else {

            super.onDraw(canvas);
        }
    }

    private Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable){
            return ((BitmapDrawable)drawable).getBitmap();
        }else if (drawable instanceof ColorDrawable){
            Rect rect = drawable.getBounds();
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;
            int color = ((ColorDrawable)drawable).getColor();
            Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(Color.alpha(color),Color.red(color), Color.green(color), Color.blue(color));
            return bitmap;
        }else {
            return null;
        }
    }


    /**
     * 绘制圆角
     * @param source
     * @return
     */
    private Bitmap createRoundConerImage(Bitmap source) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * 绘制圆形
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