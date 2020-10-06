package pres.yao.zuoye8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @ClassName DrawPathView
 * @Description TOOD
 * Date 2020/10/6 16:03
 **/
public class DrawPathView extends View {


    private Paint mLinePaint;
    private Path mPath;
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    public DrawPathView(Context context) {
        this(context, null);
    }

    public DrawPathView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public DrawPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //线的Paint
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(5);
        mLinePaint.setColor(Color.BLACK);

        //路径
        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 手指按下时
     * @param x
     * @param y
     */
    private void touchDown(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    /**
     * 手指移动时
     * @param x
     * @param y
     */
    private void touchMove(float x, float y) {

        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        //两点之间的距离大于等于4时，生成贝塞尔绘制曲线
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            //设置贝塞尔曲线的操作点为起点和终点的一半
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    /**
     * 手指抬起时
     */
    private void touchUp() {
        mPath.lineTo(mX, mY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mLinePaint);
    }
}