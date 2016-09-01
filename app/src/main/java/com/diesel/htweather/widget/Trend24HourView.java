package com.diesel.htweather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.util.ViewUtils;

import java.util.ArrayList;
import java.util.Collections;
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

    private ArrayList<Integer> xPoints = new ArrayList<>(); // 横坐标点的位置

    private float mCircleRadius; // 点 的半径

    private int width; //横坐标

    private int height; // 纵坐标

    private List<Integer> temperatures = new ArrayList<>(); // 24小时温度集合

//    private ArrayList<Weather24Hour> weather24Hours = null; // 24小时数据

    //最高温和最低温
    private int mMinTemperature;

    private int mMaxTemperature;

    private int todayIndex = 1; // 在24小时中现在的位置


    private int NowColor = getResources().getColor(R.color.common_light_blue_txt_color);

    private int PreColor = Color.GRAY;

    private int OtherColor = Color.WHITE;

    private int topBottomMargin = 20; // 曲线与上下边距

    private int mTemperatureDiff = 1; // 温度差

    Context context;

    String tempWeather = "";

    int reduce;

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
        reduce = context.getResources().getDimensionPixelOffset(R.dimen.trend24_weathericon_reduce);
        mSpace = ViewUtils.dip2px(getContext(), 50);

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
        mTextPaint.setTextSize(20);
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
    public void setTemperatures(List<Integer> temperatures) {
        this.temperatures = temperatures;
        mMaxTemperature = Collections.max(this.temperatures);
        mMinTemperature = Collections.min(this.temperatures);
        mTemperatureDiff = mMaxTemperature - mMinTemperature;
        if (mTemperatureDiff == 0) {
            mTemperatureDiff = 1;
        }
        Log.i(tag, "setTemperatures maxTemp(" + mMaxTemperature + "), minTemp(" + mMinTemperature
                + "), tempDiff(" + mTemperatureDiff + ")");
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

        for (int i = 0; i < tempSize; i++) {
            xPoints.add((width * (2 * i + 1)) / (tempSize * 2));
        }

        if (2 * topBottomMargin > this.getHeight() / 2) { // 如果上下边距大于总宽度
            topBottomMargin = this.height / 6;
        }
        int y = this.height - 2 * topBottomMargin; // 总高度减去上下边距
        // 每一度的高度（TrendView平分为 tempFall份,再除2表示将高度单位距离减小，趋势就变缓一些）
        int oneTempHeight = y / mTemperatureDiff / 2;

        PreColor = getResources().getColor(R.color.weather_trend_forward_day_color);

        //画四条线
        int y1 = height / 4;
        int y2 = height / 2;
        int y3 = height * 3 / 4;
        int y4 = height;
        int lineStopX = xPoints.get(tempSize - 1) + xPoints.get(0);
        canvas.drawLine(0, y1, lineStopX, y1, bgLinePaint);
        canvas.drawLine(0, y2, lineStopX, y2, bgLinePaint);
        canvas.drawLine(0, y3, lineStopX, y3, bgLinePaint);
        canvas.drawLine(0, y4, lineStopX, y4, bgLinePaint);
        Log.d(tag,
                "onDraw() 画四条线 stopX(" + lineStopX + "), y1(" + y1 + "), y2(" + y2 + "), y3("
                        + y3 + "), y4(" + y4 + ")");

        int leftRightMargin = 20;
//        int placeHeight = ViewUtils.dip2px(getContext(), 30) / 2;
//        int perHeight = (height - placeHeight) / mTemperatureDiff;

        int startX = leftRightMargin;
        int stopX = startX + mSpace;
        int startY = 0;
        int stopY = 0;
        for (int i = 0; i < tempSize; i++) {
            if (i != tempSize - 1) {
                startY = y - (temperatures.get(i) - mMinTemperature) * oneTempHeight
                        - topBottomMargin / 2;
                stopY = y - (temperatures.get(i + 1) - mMinTemperature) * oneTempHeight
                        - topBottomMargin / 2;
                canvas.drawLine(startX, startY, stopX, stopY, mLinePaint);
            } else {
                startY = stopY;
            }
            canvas.drawCircle(startX, startY, mCircleRadius, mPointPaint);
            startX = stopX;
            stopX = startX + mSpace;
        }

//        for (int i = 0; i < tempSize; i++) {
//            if (i != tempSize - 1) {
//                if (temperatures.get(i + 1) != 100
//                        && temperatures.get(i) != 100) { // 若温度值不为100(异常)，异常数据不划线
//
//                    if (i + 1 <= todayIndex) {
//                        mLinePaint.setColor(PreColor);
//                    } else {
//                        mLinePaint.setColor(OtherColor);
//                    }
//
//                    // 连接两点画折线
//                    int startY = y - ((temperatures.get(i) - mMinTemperature) * oneTempHeight)
//                            - topBottomMargin / 2; // 起点 总高度 - 度数*每度高度 + 上边距
//                    int endY = y - ((temperatures.get(i + 1) - mMinTemperature) * oneTempHeight)
//                            - topBottomMargin / 2;
//                    canvas.drawLine(
//                            xPoints.get(i), startY,
//                            xPoints.get(i + 1), endY,
//                            mLinePaint);
//
//                } else if (temperatures.get(i) != 100) {
//
//                    canvas.drawCircle(xPoints.get(i), y
//                            - ((temperatures.get(i) - mMinTemperature) * oneTempHeight)
//                            - topBottomMargin / 2, mCircleRadius, mPointPaint);
//
//                    continue;
//                }
//            }
//
//            canvas.drawCircle(xPoints.get(i), y
//                            - ((temperatures.get(i) - mMinTemperature) * oneTempHeight)
//                            - topBottomMargin / 2,
//                    mCircleRadius, mPointPaint);
//        }
    }

    //网格的宽度与高度
    private int gridspace_width;

    private int gridspace_heigh;

    //底部空白的高度
    private int brokenline_bottom;

    private int heigh;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        gridspace_width = mSpace;
        if (temperatures.size() == 0) {
            //通过调用onMeasure源码中的方法进行获得宽度，
            //宽度的获得有三种模式，具体介绍见博客底部连接
            width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        } else {
            //根据数据条数设置宽度
            width = gridspace_width * temperatures.size() + 10;
        }

        heigh = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        //设置底部白色空白的宽度
        brokenline_bottom = 50;

        gridspace_heigh = (heigh - brokenline_bottom) / 4;
        setMeasuredDimension(width, heigh);
    }
}
