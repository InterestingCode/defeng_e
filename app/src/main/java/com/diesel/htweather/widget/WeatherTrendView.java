package com.diesel.htweather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.util.ViewUtils;

import java.util.Collections;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class WeatherTrendView extends View {

    private static final String TAG = "WeatherTrendView";

    private String mExampleString; // TODO: use a default from R.string...

    private int mExampleColor = Color.RED; // TODO: use a default from R.color...

    private float mExampleDimension = 0; // TODO: use a default from R.dimen...

    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;

    private float mTextWidth;

    private float mTextHeight;

    private Paint mBgLinePaint, mT; // 背景线 画笔

    private Paint mHighTempLinePaint, mLowTempLinePaint; // 折线 画笔

    private Paint mPointPaint; // 点 画笔

    private List<Integer> mHighTemperatures, mLowTemperatures;

    private int mMaxTemperature, mMinTemperature, mTemperatureDiff;

    private float mCircleRadius;

    public WeatherTrendView(Context context) {
        super(context);
        init();
    }

    public WeatherTrendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeatherTrendView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mCircleRadius = ViewUtils.dip2px(getContext(), 4);
        mBgLinePaint = new Paint();
        mBgLinePaint.setAntiAlias(true);
        mBgLinePaint.setStrokeWidth(ViewUtils.dip2px(getContext(), 1));
        mBgLinePaint.setStyle(Paint.Style.FILL);
        mBgLinePaint
                .setColor(ContextCompat.getColor(getContext(), R.color.horizontal_divider_line));

        mT = new Paint();
        mT.setAntiAlias(true);
        mT.setStrokeWidth(ViewUtils.dip2px(getContext(), 1));
        mT.setStyle(Paint.Style.FILL);
        mT.setColor(ContextCompat.getColor(getContext(), R.color.gray_999));

        mHighTempLinePaint = new Paint();
        mHighTempLinePaint.setAntiAlias(true);
        mHighTempLinePaint.setStrokeWidth(ViewUtils.dip2px(getContext(), 2));
        mHighTempLinePaint.setStyle(Paint.Style.FILL);
        mHighTempLinePaint
                .setColor(ContextCompat.getColor(getContext(), R.color.high_temperature_line));

        mLowTempLinePaint = new Paint();
        mLowTempLinePaint.setAntiAlias(true);
        mLowTempLinePaint.setStrokeWidth(ViewUtils.dip2px(getContext(), 2));
        mLowTempLinePaint.setStyle(Paint.Style.FILL);
        mLowTempLinePaint
                .setColor(ContextCompat.getColor(getContext(), R.color.low_temperature_line));

        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setColor(ContextCompat.getColor(getContext(), R.color.high_temperature_line));
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    public void setTemperatures(@NonNull List<Integer> high, @NonNull List<Integer> low) {
        Log.d(TAG, "setTemperatures() high="+high);
        Log.d(TAG, "setTemperatures() low="+low);
        mHighTemperatures = high;
        mLowTemperatures = low;
        mMaxTemperature = Math.max(Collections.max(high), Collections.max(low));
        mMinTemperature = Math.min(Collections.min(high), Collections.min(low));
        mTemperatureDiff = (mMaxTemperature - mMinTemperature == 0) ? 1
                : (mMaxTemperature - mMinTemperature + 1);
        Log.i(TAG, "setTemperatures maxTemp(" + mMaxTemperature + "), minTemp(" + mMinTemperature
                + "), tempDiff(" + mTemperatureDiff + ")");
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
        Log.v(TAG, "onDraw() paddingLeft(" + paddingLeft + "), paddingTop(" + paddingTop
                + "), paddingRight(" + paddingRight + "), paddingBottom(" + paddingBottom
                + "), contentWidth(" + contentWidth + "), contentHeight(" + contentHeight + ")");
        if (contentWidth <= 0 || contentHeight <= 0) {
            return;
        }
        // draw vertical line
        int perWidth = contentWidth / 6;
        for (int i = 1; i < 6; i++) {
            canvas.drawLine(perWidth * i, 0, perWidth * i, contentHeight, mBgLinePaint);
        }

        // draw temperature broken line
        int placeHeight = ViewUtils.dip2px(getContext(), 30) / 2;
        int perHeight = (contentHeight - placeHeight) / mTemperatureDiff;
        Log.i(TAG, "onDraw() perWidth("+perWidth+"), perHeight("+perHeight+")");

//        // FIXME:delete
//        for (int i = 0; i < mTemperatureDiff; i ++) {
//            canvas.drawLine(0, perHeight*(i+1), contentWidth, perHeight*(i+1), mT);
//        }
        int startX = perWidth / 2;
        int stopX = startX + perWidth;
        int highStartY = 0, highStopY = 0, lowStartY = 0, lowStopY = 0;
        int highTempSize = mHighTemperatures.size() > 6 ? 6 : mHighTemperatures.size();
        for (int i = 0; i < highTempSize; i++) {
            if (i != highTempSize - 1) {
                int highCurrTemp = mHighTemperatures.get(i);
                int highNextTemp = mHighTemperatures.get(i + 1);
                int lowCurrTemp = mLowTemperatures.get(i);
                int lowNextTemp = mLowTemperatures.get(i + 1);
                highStartY = (mMaxTemperature - highCurrTemp + 1) * perHeight;
                highStopY = (mMaxTemperature - highNextTemp + 1) * perHeight;
                lowStartY = (mMaxTemperature - lowCurrTemp + 1) * perHeight;
                lowStopY = (mMaxTemperature - lowNextTemp + 1) * perHeight;
                Log.d(TAG,
                        "onDraw() highCurrTemp(" + highCurrTemp + "), highNextTemp(" + highNextTemp
                                + "), startX(" + startX + "), stopX(" + stopX + "), highStartY("
                                + highStartY + "), highStopY(" + highStopY + ")");

                canvas.drawLine(startX, highStartY, stopX, highStopY, mHighTempLinePaint);
                canvas.drawLine(startX, lowStartY, stopX, lowStopY, mLowTempLinePaint);
            } else { // just last point
                highStartY = highStopY;
                lowStartY = lowStopY;
            }

            mPointPaint.setColor(ContextCompat.getColor(getContext(), R.color.high_temperature_line));
            canvas.drawCircle(startX, highStartY, mCircleRadius, mPointPaint);

            mPointPaint.setColor(ContextCompat.getColor(getContext(), R.color.low_temperature_line));
            canvas.drawCircle(startX, lowStartY, mCircleRadius, mPointPaint);

            startX = stopX;
            stopX = startX + perWidth;
        }
    }

}
