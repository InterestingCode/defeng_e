package com.diesel.htweather.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.response.FarmingResJO;

import java.util.ArrayList;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/28
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class WeatherTrendHelper {

    private LinearLayout[] mWeatherLayouts;

    private LinearLayout[] mWindLayouts;

    private Context mContext;

    LinearLayout mWeatherLayout, mWindLayout;

//    private List<WeatherTrendBean> mWeatherTrendData;

    private ArrayList<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> mWeatherTrendData;

    private int mDayCnt;

    public WeatherTrendHelper(Context context, LinearLayout weatherLayout, LinearLayout windLayout,
            ArrayList<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> weatherTrendData) {
        mContext = context;
        mWeatherLayout = weatherLayout;
        mWindLayout = windLayout;
        mWeatherTrendData = weatherTrendData;

        mDayCnt = mWeatherTrendData.size();
        mWeatherLayouts = new LinearLayout[mDayCnt];
        mWindLayouts = new LinearLayout[mDayCnt];

        ViewTreeObserver vto = mWeatherLayout.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            boolean hasMeasured;

            @Override
            public boolean onPreDraw() {
                if (!hasMeasured) {
                    hasMeasured = true;
                    addChildView(mWeatherLayout.getMeasuredWidth());
                    mWeatherLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                return false;
            }
        });
    }

    private void addChildView(int parentWidth) {
        int childWidth = parentWidth / mDayCnt;
        Log.d("WeatherTrendHelper", "addChildView() parentWidth=" + parentWidth);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (int i = 0; i < mDayCnt; i++) {
            FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity entity = mWeatherTrendData.get(i);

            View weatherChild = inflater.inflate(R.layout.weather_trend_data_item_layout, null);
            TextView weekTv = (TextView) weatherChild.findViewById(R.id.week_tv);
            TextView dateTv = (TextView) weatherChild.findViewById(R.id.date_tv);
            TextView weatherTv = (TextView) weatherChild.findViewById(R.id.weather_or_wind_tv);
            weekTv.setText(entity.week);
            dateTv.setText(DateUtils.formatDate(entity.currDate, DateUtils.MM_DD));
            weatherTv.setText(entity.weatherContent);
//            setTextValue(weekTv, dateTv, i);
            ViewGroup.LayoutParams weatherLp = weatherChild.getLayoutParams();
            if (null == weatherLp) {
                weatherLp = new ViewGroup.LayoutParams(childWidth,
                        ViewGroup.LayoutParams.MATCH_PARENT);
            } else {
                weatherLp.width = childWidth;
            }
            mWeatherLayout.addView(weatherChild, weatherLp);

            View windChild = inflater.inflate(R.layout.weather_trend_data_item_layout, null);
            TextView windTv = (TextView) windChild.findViewById(R.id.weather_or_wind_tv);
            windChild.findViewById(R.id.week_tv).setVisibility(View.GONE);
            windChild.findViewById(R.id.date_tv).setVisibility(View.GONE);
            windTv.setText(entity.windPower);
            ViewGroup.LayoutParams windLp = windChild.getLayoutParams();
            if (null == windLp) {
                windLp = new ViewGroup.LayoutParams(childWidth,
                        ViewGroup.LayoutParams.MATCH_PARENT);
            } else {
                windLp.width = childWidth;
            }
            mWindLayout.addView(windChild, windLp);
        }
    }

    private void setTextValue(TextView weekTv, TextView dateTv, int i) {
        switch (i) {
            case 0:
                weekTv.setText("明天");
                dateTv.setText("09/10");
                break;
            case 1:
                weekTv.setText("周六");
                dateTv.setText("09/11");
                break;
            case 2:
                weekTv.setText("周日");
                dateTv.setText("09/12");
                break;
            case 3:
                weekTv.setText("周一");
                dateTv.setText("09/13");
                break;
            case 4:
                weekTv.setText("周二");
                dateTv.setText("09/14");
                break;
            case 5:
                weekTv.setText("周三");
                dateTv.setText("09/15");
                break;
        }
    }
}
