package com.diesel.htweather.farming.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.model.WeatherDataBean;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.DateUtils;
import com.diesel.htweather.util.ViewUtils;
import com.diesel.htweather.widget.Trend24HourView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @BindView(R.id.weather_desc_tv)
    TextView mWeatherDescTv;

    @BindView(R.id.weather_date_tv)
    TextView mWeatherDateTv;

    @BindView(R.id.weather_tips_tv)
    TextView mWeatherTipsTv;

    @BindView(R.id.weather_icon_iv)
    ImageView mWeatherIconIv;

    @BindView(R.id.close_tips_iv)
    ImageView mCloseTipsIv;

    @BindView(R.id.truth_data_view)
    Trend24HourView mTruthDataView;

    @BindView(R.id.weather_tips_layout)
    LinearLayout mTipsLayout;

    @BindView(R.id.truth_data_top_layout)
    LinearLayout mTruthDataTopLayout;

    @BindView(R.id.truth_data_bottom_layout)
    RelativeLayout mTruthDataBottomLayout;

    Context mContext;

    WeatherDataBean mWeatherData;

    FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity mHoursDataList;

    public WeatherDataHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void bindData(WeatherDataBean bean) {
        mWeatherData = bean;

        // 7天天气数据
        if (null != bean.dayWeatherList && !bean.dayWeatherList.isEmpty()) {
            for (int i = 0; i < bean.dayWeatherList.size(); i ++) {
                FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity entity = bean.dayWeatherList.get(i);
                if (DateUtils.isToday(entity.currDate)) {
                    float temp = Float.valueOf(entity.currTemp);
                    mTemperatureTv.setText(String.valueOf((int) temp));
                    mWeatherDescTv.setText(entity.weatherContent+" "+entity.tempBucket+" "+entity.windPower);
                    mWeatherDateTv.setText(entity.currDate+" ("+entity.currLunarDate+") "+entity.week);
                    break;
                }
            }
        }

        // tips
        if (!TextUtils.isEmpty(mWeatherData.tips)) {
            ViewUtils.visible(mTipsLayout);
            mWeatherTipsTv.setText(mWeatherData.tips);
        } else {
            ViewUtils.gone(mTipsLayout);
        }

        // 实况数据
        mHoursDataList = bean.hoursDataList;
        setTemperatureView();
    }

    @OnClick({R.id.more_btn, R.id.close_tips_iv, R.id.truth_data_btn, R.id.truth_data_setting_iv,
            R.id.air_temp_tv, R.id.rainfall_tv, R.id.soil_temp_tv, R.id.soil_humidity_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_btn:
                ActivityNav.getInstance().startWeatherTrendActivity(mContext);
                break;
            case R.id.close_tips_iv:
                ViewUtils.gone(mTipsLayout);
                break;
            case R.id.truth_data_setting_iv:
                ActivityNav.getInstance().startTruthDataSettingActivity(mContext);
                break;
            case R.id.truth_data_btn:
                if (mTruthDataTopLayout.getVisibility() == View.GONE) {
                    ViewUtils.visible(mTruthDataTopLayout);
                    LinearLayout.LayoutParams lp
                            = (LinearLayout.LayoutParams) mTruthDataBottomLayout.getLayoutParams();
                    lp.topMargin = -ViewUtils.dip2px(mContext, 70);
                    mTruthDataBottomLayout.setLayoutParams(lp);
                } else if (mTruthDataTopLayout.getVisibility() == View.VISIBLE) {
                    ViewUtils.gone(mTruthDataTopLayout);
                    LinearLayout.LayoutParams lp
                            = (LinearLayout.LayoutParams) mTruthDataBottomLayout.getLayoutParams();
                    lp.topMargin = 0;
                    mTruthDataBottomLayout.setLayoutParams(lp);
                }
                break;
            case R.id.air_temp_tv: // 气温
                setTemperatureView();
                break;
            case R.id.rainfall_tv: // 降水
                setRainfallView();
                break;
            case R.id.soil_temp_tv: // 土壤温度
                setSoilTempView();
                break;
            case R.id.soil_humidity_tv: // 土壤湿度
                setSoilHumidityView();
                break;
        }
    }

    private void setSoilHumidityView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.SoilMoistureListEntity>
                    soilMoistureList = mHoursDataList.soilMoistureList;
            if (null != soilMoistureList && !soilMoistureList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                for (int i = 0; i <soilMoistureList.size(); i ++) {
                    float temp = Float.valueOf(soilMoistureList.get(i).value);
                    temperatures.add((int) temp);
                }
                mTruthDataView.setTemperatures(temperatures);
            }
        }
    }

    private void setSoilTempView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.SoilTempLlistEntity>
                    soilTempLlist = mHoursDataList.soilTempLlist;
            if (null != soilTempLlist && !soilTempLlist.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                for (int i = 0; i <soilTempLlist.size(); i ++) {
                    float temp = Float.valueOf(soilTempLlist.get(i).value);
                    temperatures.add((int) temp);
                }
                mTruthDataView.setTemperatures(temperatures);
            }
        }
    }

    private void setRainfallView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.PrecipitationListEntity>
                    precipitationList = mHoursDataList.precipitationList;
            if (null != precipitationList && !precipitationList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                for (int i = 0; i < precipitationList.size(); i ++) {
                    float temp = Float.valueOf(precipitationList.get(i).value);
                    temperatures.add((int) temp);
                }
                mTruthDataView.setTemperatures(temperatures);
            }
        }
    }

    private void setTemperatureView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.AirTempListEntity>
                    airTempList = mHoursDataList.airTempList;
            if (null != airTempList && !airTempList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                for (int i = 0; i < airTempList.size(); i ++) {
                    float temp = Float.valueOf(airTempList.get(i).value);
                    temperatures.add((int) temp);
                }
                mTruthDataView.setTemperatures(temperatures);
            }
        }
    }
}
