package com.diesel.htweather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.model.Weather24Hour;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

    private ArrayList<Integer> xPoints; // 横坐标点的位置

    private float circleRadius = 8; // 点 的半径

    private int width; //横坐标

    private int height; // 纵坐标

    private List<Integer> temperatures = new ArrayList<>(); // 24小时温度集合

//    private ArrayList<Weather24Hour> weather24Hours = null; // 24小时数据

    //最高温和最低温
    private int minTemp;

    private int maxTemp;

    private int todayIndex = 1; // 在24小时中现在的位置


    private int NowColor = getResources().getColor(R.color.common_light_blue_txt_color);

    private int PreColor = Color.GRAY;

    private int OtherColor = Color.WHITE;

    private int topBottomMargin = 20; // 曲线与上下边距

    private int tempFall = 1; // 温度差

    Context context;

    String tempWeather = "";

    int reduce;


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
        circleRadius = context.getResources().getDimensionPixelOffset(R.dimen.trend_radius_size);
        reduce = context.getResources().getDimensionPixelOffset(R.dimen.trend24_weathericon_reduce);

        // 初始化各个画笔
        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setColor(Color.WHITE);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setStrokeWidth(
                context.getResources().getDimensionPixelOffset(R.dimen.trend_line_size));
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
        bgLinePaint.setColor(getResources().getColor(R.color.bg_white_with_90per_alaph));

    }

//    //获取天气图标
//    private Bitmap getWeatherIconBitmap(int index, int flag, String weathersign) {
//        Bitmap bitmap = null;
//        int sign = Integer.parseInt(weathersign);
//        if (index < weather24Hours.size()) {
//            String forcast = weather24Hours.get(index).forcastdate;
//            //LogUtil.i(tag, "timetime=="+forcast);
//            int resId = ImageUtil.getTrendWeatherDayIcon(flag, sign);
//            if (isNight(forcast) && (weathersign.equals("1") || weathersign.equals("2"))) {
//                resId = ImageUtil.getTrendWeatherNightIcon(flag, sign);
//            }
//            bitmap = BitmapFactory.decodeResource(getResources(), resId);
//            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 3 / 4,
//                    bitmap.getHeight() * 2 / 3, true);
//        }
//        return bitmap;
//    }

    //判断白天还是晚上
    boolean isNight(String time) {
                /*白天：6：00—17:59
                        晚上：18:00—5:59*/
        DateFormat df = new SimpleDateFormat("HH:mm");
        Date date;
        boolean isNight = false;
        try {
            date = df.parse(time);
            int hours = date.getHours();
            if (hours > 6 && hours < 18) {
                isNight = false;
            } else {
                isNight = true;
            }
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return isNight;
    }

    /**
     * 设置温度
     */
    public void setTemperatures(List<Integer> temp/*, ArrayList<Weather24Hour> weather24Hours*/) {
        this.temperatures = temp;
//        this.temperatures.addAll(temp);
//        this.weather24Hours = weather24Hours;
        maxTemp = Collections.max(temperatures);
        minTemp = Collections.min(temperatures);
        tempFall = maxTemp - minTemp;
        if (tempFall == 0) {
            tempFall = 1;
        }
        Log.i(tag, "最高温=" + maxTemp + ",最低温=" + minTemp + " ,高低温差=" + tempFall+", 温度个数"+temperatures.size());
//        invalidate();
//        postInvalidate();
        postInvalidateDelayed(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.width = this.getWidth();

        xPoints = new ArrayList<>();
        int tempSize = temperatures.size();
        for (int i = 0; i < tempSize; i++) {
            xPoints.add((width * (2 * i + 1)) / (tempSize * 2));
        }

        this.height = this.getHeight();

        if (2 * topBottomMargin > this.getHeight() / 2) { // 如果上下边距大于总宽度
            topBottomMargin = this.height / 6;
        }
        int y = this.height - 2 * topBottomMargin; // 总高度减去上下边距
        int oneTempHeight = y / tempFall
                / 2; // 每一度的高度（TrendView平分为 tempFall份,再除2表示将高度单位距离减小，趋势就变缓一些）

        PreColor = getResources().getColor(R.color.weather_trend_forward_day_color);

        //画四条线
        int y1 = height / 4;
        int y2 = height / 2;
        int y3 = height * 3 / 4;
        int y4 = height;
        Log.d(tag, "onDraw() 画四条线之前 y1("+y1+"), y2("+y2+"), y3("+y3+"), y4("+y4+"), tempSize("+tempSize+")");
        if (tempSize != 0) {
            int endLineX = xPoints.get(tempSize - 1) + xPoints.get(0);
            canvas.drawLine(0, y1, endLineX, y1, bgLinePaint);
            canvas.drawLine(0, y2, endLineX, y2, bgLinePaint);
            canvas.drawLine(0, y3, endLineX, y3, bgLinePaint);
            canvas.drawLine(0, y4, endLineX, y4, bgLinePaint);
            Log.d(tag, "onDraw() 画四条线 endLineX("+endLineX+"), y1("+y1+"), y2("+y2+"), y3("+y3+"), y4("+y4+")");
        }

        for (int i = 0; i < tempSize; i++) {
            //设置xx时间的画笔
            if (todayIndex == i) { // 今天
                mPointPaint.setColor(OtherColor);
                mTextPaint.setColor(OtherColor);
            } else if (todayIndex < i) {
                mPointPaint.setColor(OtherColor);
                mTextPaint.setColor(OtherColor);
            } else if (todayIndex > i) {
                mPointPaint.setColor(PreColor);
                mTextPaint.setColor(PreColor);
            }

            if (i != tempSize - 1) {

                if (temperatures.get(i + 1) != 100
                        && temperatures.get(i) != 100) { // 若温度值不为100(异常)，异常数据不划线

                    if (i + 1 <= todayIndex) {
                        mLinePaint.setColor(PreColor);
                    } else {
                        mLinePaint.setColor(OtherColor);
                    }

                    // 连接两点画折线
                    int startY = y - ((temperatures.get(i) - minTemp) * oneTempHeight)
                            - topBottomMargin / 2; // 起点 总高度 - 度数*每度高度 + 上边距
                    int endY = y - ((temperatures.get(i + 1) - minTemp) * oneTempHeight)
                            - topBottomMargin / 2;
                    canvas.drawLine(
                            xPoints.get(i), startY,
                            xPoints.get(i + 1), endY,
                            mLinePaint);

                } else if (temperatures.get(i) != 100) {

                    canvas.drawCircle(xPoints.get(i), y
                            - ((temperatures.get(i) - minTemp) * oneTempHeight)
                            - topBottomMargin / 2, circleRadius, mPointPaint);

                    continue;
                }
            }

//            //绘制天气图标
//            if (i == 0) {//绘制第一个图标
//                Weather24Hour weather24Hour = weather24Hours.get(0);
//                tempWeather = weather24Hour.weathersign;
//                Bitmap bitmap = getWeatherIconBitmap(0, 0, tempWeather);
//                if (bitmap != null) {
//                    canvas.drawBitmap(bitmap, xPoints.get(0) - bitmap.getWidth() / 2, y
//                            - ((temperatures.get(0) - minTemp) * oneTempHeight + topBottomMargin
//                            + bitmap.getHeight() - reduce), null);
//                }
//            } else {
//                //天气变化则绘制图标
//                Weather24Hour obj = weather24Hours.get(i);
//                if (!tempWeather.equals("") && !tempWeather.equals(obj.weathersign)) {
//                    tempWeather = obj.weathersign;
//                    Bitmap bitmap = getWeatherIconBitmap(i, AppConstants.TREND_OTHERS, tempWeather);
//                    if (bitmap != null) {
//                        canvas.drawBitmap(bitmap, xPoints.get(i) - bitmap.getWidth() / 2, y
//                                - ((temperatures.get(i) - minTemp) * oneTempHeight + topBottomMargin
//                                + bitmap.getHeight() - reduce), null);
//                    }
//                }
//            }

            canvas.drawCircle(xPoints.get(i), y
                            - ((temperatures.get(i) - minTemp) * oneTempHeight) - topBottomMargin / 2,
                    circleRadius, mPointPaint);
        }
    }

    /**
     * 设置上下边距
     */
    public void setMargin(int margin) {
        this.topBottomMargin = margin;
    }

    public int getMargin() {
        return topBottomMargin;
    }

}
