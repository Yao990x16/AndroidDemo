package pres.yao.zuoye8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName DrawView
 * @Description TOOD
 * Date 2020/10/6 15:53
 **/
public class DrawView extends View {
    private int x_begin;
    private int y_begin;
    private int x_end;
    private int y_end;
    private Canvas can = null;
    private Bitmap bit;


    public DrawView(Context context) {
        super(context);
        //this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
//		invalidate();
        bit =  Bitmap.createBitmap(880,480, Bitmap.Config.ARGB_8888);
        can = new Canvas();
        can.setBitmap(bit);
    }

    public DrawView(Context context, AttributeSet attribute) {
        super(context,attribute);
        bit =  Bitmap.createBitmap(481,880, Bitmap.Config.ARGB_8888);
        can = new Canvas();
        can.setBitmap(bit);

    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bit, 0, 0,null);

        //canvas.drawCircle(x, y, 15, p);
        //canvas.drawLine(x_begin, y_begin, x_end, y_end, p);
    }

    public boolean  onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x_begin = (int) event.getX();
            y_begin = (int) event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x_end = (int) event.getX();
            y_end = (int) event.getY();
            Paint p = new Paint();
            p.setColor(Color.RED);
            p.setStrokeWidth(10);
            can.drawLine(x_begin, y_begin, x_end, y_end, p);
            invalidate();
            x_begin = x_end;
            y_begin = y_end;
        }
        return true;
    }
}
