package com.diesel.htweather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.util.ViewUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/22
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class Trend24HourView extends View {

    private static final String tag = Trend24HourView.class.getSimpleName();

    private Paint mPointPaint; // 点 画笔

    private Paint mTextPaint; // 温度 画笔

    private Paint mLinePaint; // 折线 画笔

    private Paint bgLinePaint; // 背景线 画笔

    private float mCircleRadius; // 点 的半径

    private int width; //横坐标

    private int height; // 纵坐标

    private List<Integer> temperatures = new ArrayList<>(); // 24小时温度集合

    private List<String> mHours = new ArrayList<>(); // x

    private List<Integer> mValues = new ArrayList<>(); // y

    //最高温和最低温
    private int mMinTemperature;

    private int mMaxTemperature;

    private int topBottomMargin = 20; // 曲线与上下边距

    private int leftRightMargin = 0; // 折线左右间距

    private int mTemperatureDiff = 1; // 温度差

    private int mGridNumber = 1; //

    private int timeHeight;

    private int unitWidth;

    Context context;

    private int mSpace;

    public Trend24HourView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public Trend24HourView(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
        init();
    }

    private void init() {
        mCircleRadius = ViewUtils.dip2px(getContext(), 4);
        mSpace = ViewUtils.dip2px(getContext(), 50);
        timeHeight = ViewUtils.dip2px(getContext(), 22.5f);
        unitWidth = ViewUtils.dip2px(getContext(), 30);

        // 初始化各个画笔
        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setColor(Color.WHITE);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(ViewUtils.dip2px(getContext(), 1.5f));
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setColor(Color.WHITE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(ViewUtils.dip2px(getContext(), 12));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.WHITE);

        bgLinePaint = new Paint();
        bgLinePaint.setAntiAlias(true);
        bgLinePaint.setStrokeWidth(1.0f);
        bgLinePaint.setStyle(Paint.Style.FILL);
        bgLinePaint.setColor(getResources().getColor(R.color.bg_white_with_90per_alpha));
    }

    /**
     * 设置温度
     */
    public void setTemperatures(List<Integer> temperatures, List<String> hours) {
        this.temperatures = temperatures;
        this.mHours = hours;
        mValues.clear();
        mMaxTemperature = Collections.max(this.temperatures);
        mMinTemperature = Collections.min(this.temperatures);
        mGridNumber = mTemperatureDiff = mMaxTemperature - mMinTemperature;
        if (mTemperatureDiff == 0) {
            mTemperatureDiff = 1;
            mGridNumber = 1;
        }
        if (mGridNumber > 5) {
            mGridNumber = 5;
        }

        if (mGridNumber == 1) {
            mValues.add(mMinTemperature);
        } else if (mGridNumber > 1) {
            mValues.add(mMinTemperature);
            for (int i = 1; i < mGridNumber -1; i ++) {
                mValues.add(mMinTemperature + i * mTemperatureDiff / mGridNumber);
            }
            mValues.add(mMaxTemperature);
        }

        Collections.sort(mValues, new Comparator<Integer>() {
            @Override
            public int compare(Integer t0, Integer t1) {
                return t1.compareTo(t0);
            }
        });

        Log.i(tag, "setTemperatures() maxTemp(" + mMaxTemperature + "), minTemp(" + mMinTemperature
                + "), tempDiff(" + mTemperatureDiff + "), mValues="+mValues);
        requestLayout();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.width = getWidth();
        this.height = getHeight();

        int tempSize = temperatures.size();
        if (tempSize == 0) {
            return;
        }

        // draw four horizontal divider line
        int dividerLineStartX = leftRightMargin / 2 + unitWidth;
        int dividerLineStopX = width - leftRightMargin / 2;
//        int y1 = gridSpaceHeight + topBottomMargin;
//        int y2 = gridSpaceHeight * 2 + topBottomMargin;
//        int y3 = gridSpaceHeight * 3 + topBottomMargin;
//        int y4 = gridSpaceHeight * 4 + topBottomMargin;
//        int y5 = gridSpaceHeight * 5 + topBottomMargin;
//        canvas.drawLine(dividerLineStartX, y1, dividerLineStopX, y1, bgLinePaint);
//        canvas.drawLine(dividerLineStartX, y2, dividerLineStopX, y2, bgLinePaint);
//        canvas.drawLine(dividerLineStartX, y3, dividerLineStopX, y3, bgLinePaint);
//        canvas.drawLine(dividerLineStartX, y4, dividerLineStopX, y4, bgLinePaint);
//        canvas.drawLine(dividerLineStartX, y5, dividerLineStopX, y5, bgLinePaint);

        // draw unit value
        int unitStartX = unitWidth / 2;
//        canvas.drawText("39°", unitStartX, y1, mTextPaint);
//        canvas.drawText("37°", unitStartX, y2, mTextPaint);
//        canvas.drawText("35°", unitStartX, y3, mTextPaint);
//        canvas.drawText("34°", unitStartX, y4, mTextPaint);
//        canvas.drawText("32°", unitStartX, y5, mTextPaint);

        for (int i = 0; i < mGridNumber; i ++) {
            int y = gridSpaceHeight * (i + 1) + topBottomMargin;
            canvas.drawLine(dividerLineStartX, y, dividerLineStopX, y, bgLinePaint);
//            canvas.drawText(String.valueOf(mValues.get(i)), unitStartX, y, mTextPaint);
        }

        // draw vertical divider line
//        for (int i = 1; i < tempSize; i++) {
//            int startY = topBottomMargin;
//            int stopY = height - topBottomMargin - timeHeight;
//            canvas.drawLine(gridSpaceWidth * i + unitWidth, startY, gridSpaceWidth * i + unitWidth,
//                    stopY, bgLinePaint);
//        }

        // draw polyline
        int perHeight = (height - topBottomMargin * 2 - timeHeight) / mTemperatureDiff;
        float textBottom = mTextPaint.getFontMetrics().bottom;
        int textY = (int) (height - (timeHeight - textBottom) / 2);
        Log.i(tag,
                "onDraw() gridSpaceWidth=" + gridSpaceWidth + "; gridSpaceHeight=" + gridSpaceHeight
                        + "; perHeight=" + perHeight + "; width=" + width + "; height=" + height
                        + "; timeHeight=" + timeHeight + "; textBottom=" + textBottom + "; textY="
                        + textY);
        // 32, 33, 33, 35, 35, 36, 35, 37, 39, 38, 34, 33
        int startX;
        int startY;
        int stopX = 0;
        int stopY = 0;
        for (int i = 0; i < tempSize; i++) {
            if (i < tempSize - 1) {
                startX = (int) (gridSpaceWidth * (i + 0.5)) + unitWidth;
                startY = height - (temperatures.get(i) - mMinTemperature) * perHeight
                        - topBottomMargin - timeHeight;
                stopX = (int) (gridSpaceWidth * (i + 1.5)) + unitWidth;
                stopY = height - (temperatures.get(i + 1) - mMinTemperature) * perHeight
                        - topBottomMargin - timeHeight;
                canvas.drawLine(startX, startY, stopX, stopY, mLinePaint);
            } else {
                startX = stopX;
                startY = stopY;
            }
            Log.d(tag, "onDraw() start(" + startX + ", " + startY + "); stop(" + stopX + ", "
                    + stopY + "); i=" + i + "; temp=" + temperatures.get(i));

//            if (i % 2 == 0) {
//                mPointPaint.setColor(
//                        ContextCompat.getColor(getContext(), R.color.polyline_yellow_point_color));
//            } else {
//                mPointPaint.setColor(
//                        ContextCompat.getColor(getContext(), R.color.polyline_blue_point_color));
//            }
            canvas.drawCircle(startX, startY, mCircleRadius, mPointPaint);
            int valueStartY = startY - 25;
            if (startY > 0 && startY < 50) {
                valueStartY = startY + 50;
            }
            canvas.drawText(temperatures.get(i)+"", startX, valueStartY, mTextPaint);

//            float textStartY = height - (timeHeight - mTextPaint.getFontMetrics().bottom) / 2;
//            float textStartY = height - timeHeight + (timeHeight - mTextPaint.getFontMetrics().bottom);
            float textStartY = height - 20;
            canvas.drawText(mHours.get(i), startX, textStartY, mTextPaint);
            Log.i(tag, "onDraw()  draw text  startX=" + startX + "; textStartY=" + textStartY);
        }

    }

    //网格的宽度与高度
    private int gridSpaceWidth;

    private int gridSpaceHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        gridSpaceWidth = mSpace;
        width = temperatures.isEmpty() ? getDefaultSize(getSuggestedMinimumWidth(),
                widthMeasureSpec)
                : gridSpaceWidth * temperatures.size() + leftRightMargin * 2 + unitWidth;
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

//        gridSpaceHeight = (height - topBottomMargin * 2 - timeHeight) / 5;
        gridSpaceHeight = (height - topBottomMargin * 2 - timeHeight) / mGridNumber;

        Log.d(tag, "onMeasure() width="+width+"; height="+height+"; gridSpaceWidth="+gridSpaceWidth+"; gridSpaceHeight="+gridSpaceHeight);

        setMeasuredDimension(width, height);
    }
}
