package com.diesel.htweather.farming;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.model.WeatherTrendBean;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.util.DateUtils;
import com.diesel.htweather.util.IntentExtras;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.util.ViewUtils;
import com.diesel.htweather.util.WeatherTrendHelper;
import com.diesel.htweather.widget.WeatherTrendView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherTrendActivity extends BaseActivity {

    @BindView(R.id.weather_trend_view)
    WeatherTrendView mWeatherTrendView;

    @BindView(R.id.weather_layout)
    LinearLayout mWeatherLayout;

    @BindView(R.id.wind_layout)
    LinearLayout mWindLayout;

    @BindView(R.id.temperature_tv)
    TextView mTemperatureTv;

    @BindView(R.id.weather_icon_iv)
    ImageView mWeatherIconIv;

    @BindView(R.id.weather_desc_tv)
    TextView mWeatherDescTv;

    @BindView(R.id.weather_date_tv)
    TextView mWeatherDateTv;

    @BindView(R.id.weather_trend_layout)
    LinearLayout mWeatherTrendLayout;

    @BindView(R.id.weather_details_layout)
    LinearLayout mWeatherDetailsLayout;

    @BindView(R.id.update_time_tv)
    TextView mUpdateTimeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_trend);
        ButterKnife.bind(this);
//        testTempTrend();

        ArrayList<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> dayWeatherList
                = IntentExtras.getWeatherTrendData(getIntent());
        List<WeatherTrendBean> trendData = new ArrayList<>();
        if (null != dayWeatherList && !dayWeatherList.isEmpty()) {
            List<Integer> highTemp = new ArrayList<>();
            List<Integer> lowTemp = new ArrayList<>();
            for (int i = 0; i < dayWeatherList.size(); i++) {
                FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity entity
                        = dayWeatherList.get(i);
                if (DateUtils.isToday(entity.currDate)) {
                    float temp = Float.valueOf(entity.currTemp);
                    mTemperatureTv.setText(String.valueOf((int) temp));
                    mWeatherDescTv.setText(entity.weatherContent + " " + entity.tempBucket + " "
                            + entity.windPower + entity.windPowerLevel);
                    mWeatherDateTv.setText(
                            entity.currDate + " (" + entity.currLunarDate + ") " + entity.week);
                    mUpdateTimeTv.setText(getString(R.string.weather_data_update_time,
                            DateUtils.formatDate(System.currentTimeMillis(), DateUtils.HH_MM)));
                }

                WeatherTrendBean bean = new WeatherTrendBean();
                bean.forcastDate = DateUtils.formatDate(entity.currDate, DateUtils.MM_DD);
                bean.weather = entity.weatherContent;
                bean.windPower = entity.windPower;
                if (!TextUtils.isEmpty(entity.tempBucket) && entity.tempBucket.contains("/")) {
                    String[] temp = entity.tempBucket.replaceAll("°", "").split("/");
                    lowTemp.add(Integer.valueOf(temp[0]));
                    if (temp.length == 1) {
                        highTemp.add(25);
                    } else {
                        highTemp.add(Integer.valueOf(temp[1]));
                    }
                } else {
                    lowTemp.add(15);
                    highTemp.add(25);
                }
                trendData.add(bean);
            }
            mWeatherTrendView.setTemperatures(highTemp, lowTemp);
        } else {
            ViewUtils.gone(mWeatherDetailsLayout);
            ToastUtils.show("未获取到天气数据");
        }

        if (!trendData.isEmpty()) {
            ViewUtils.visible(mWeatherTrendLayout);
            WeatherTrendHelper trendHelper = new WeatherTrendHelper(this, mWeatherLayout,
                    mWindLayout,
                    trendData);
        } else {
            ViewUtils.gone(mWeatherTrendLayout);
        }

//        for (int i = 0; i < 6; i++) {
//            WeatherTrendBean bean = new WeatherTrendBean();
//            bean.forcastDate = "2016-08-28";
//            trendData.add(bean);
//        }
    }

    private void testTempTrend() {
        Integer[] temp1 = new Integer[]{31, 26, 29, 31, 35, 32};
        Integer[] temp2 = new Integer[]{23, 22, 20, 21, 27, 24};
        List<Integer> high = Arrays.asList(temp1);
        List<Integer> low = Arrays.asList(temp2);
        mWeatherTrendView.setTemperatures(high, low);
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
