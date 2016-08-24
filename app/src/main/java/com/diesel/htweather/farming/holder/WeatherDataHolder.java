package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.model.WeatherDataBean;
import com.diesel.htweather.widget.Trend24HourView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
public class WeatherDataHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.temperature_tv)
    TextView mTemperatureTv;

    @BindView(R.id.weather_icon_iv)
    ImageView mWeatherIconIv;

    @BindView(R.id.close_tips_iv)
    ImageView mCloseTipsIv;

    @BindView(R.id.truth_data_view)
    Trend24HourView mTruthDataView;

    @BindView(R.id.forecast_times_layout)
    LinearLayout mForecastTimesLayout;

    public WeatherDataHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(WeatherDataBean bean) {
        Log.d("Trend24HourView", "=============== bindData() ===============");
        if (null == bean) {
            return;
        }
        Integer[] temp = new Integer[]{32, 33, 33, 35, 35, 36, 35, 37, 39, 38, 34, 33};
        List<Integer> tempratures = Arrays.asList(temp);
//        mTruthDataView.setTemperatures(tempratures);
    }
}
