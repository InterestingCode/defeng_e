package com.diesel.htweather.farming;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.model.WeatherTrendBean;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_trend);
        ButterKnife.bind(this);
        testTempTrend();

        List<WeatherTrendBean> trendData = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            WeatherTrendBean bean = new WeatherTrendBean();
            bean.forcastDate = "2016-08-28";
            trendData.add(bean);
        }
        WeatherTrendHelper trendHelper = new WeatherTrendHelper(this, mWeatherLayout, mWindLayout,
                trendData);
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
