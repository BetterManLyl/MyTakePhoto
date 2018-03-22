package lyl.mytakephoto.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author lyl
 * @date 2018/3/20.
 */

public class Myview extends View {

    private Paint paint;
    private boolean isClick = false;

    private Context context;

    private String xName = "";

    public Myview(Context context) {
        super(context);
        this.context = context;
        init();
    }


    public void setXBottom(String xName) {
        this.xName = xName;
        postInvalidate();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setFlags(AUTOFILL_TYPE_DATE);
        paint.setTextSize(dpTopx(13));
        paint.setAntiAlias(true);
    }

    public Myview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawText("hello world", 100, 100, paint);
        drawXname(canvas);
        drawLine(canvas);
        drawFiveLines(canvas);
    }

    private void drawXname(Canvas canvas) {
        canvas.drawText(xName, 25, getHeight(), paint);
    }

    /**
     * 在x轴上画五条短线
     *
     * @param canvas
     */
    private void drawFiveLines(Canvas canvas) {
        paint.setColor(Color.BLUE);
        int width = getWidth() / 5;

        for (int i = 0; i < 5; i++) {

            canvas.drawLine((i + 1) * 25 + (i + 1) * width, getHeight() - 10, (i + 1) * 25 + (i + 1) * width, getHeight() - 30, paint);
        }
    }

    /**
     * F: 将dp的单位转成为px
     */
    private int dpTopx(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void drawLine(Canvas canvas) {
        canvas.drawText("你好啊", 25, paint.measureText("高"), paint);
        //设置线的粗细
        paint.setStrokeWidth(3);
        //y轴
        canvas.drawLine(25, paint.measureText("你") + 10, 25, getHeight() - 10, paint);
        //x轴
        canvas.drawLine(25, getHeight() - 10, getWidth() - 10, getHeight() - 10, paint);

        canvas.drawCircle(100,100,100,paint);

//        Path path=new Path();
//        path.moveTo();
//        path.lineTo();
//        path.close();
//        canvas.drawPath(path,paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                paint.setColor(Color.RED);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                paint.setColor(Color.BLACK);
                postInvalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
