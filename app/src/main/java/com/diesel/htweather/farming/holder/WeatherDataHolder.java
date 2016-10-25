package com.diesel.htweather.farming.holder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.util.ViewUtils;
import com.diesel.htweather.widget.Trend24HourView;

import java.util.ArrayList;
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

    @BindView(R.id.air_temp_tv)
    TextView mAirTempTv;

    @BindView(R.id.rainfall_tv)
    TextView mRainfallTv;

    @BindView(R.id.soil_temp_tv)
    TextView mSoilTempTv;

    @BindView(R.id.soil_humidity_tv)
    TextView mSoilHumidityTv;

    @BindView(R.id.air_humidity_tv)
    TextView mAirHumidityTv;

    @BindView(R.id.sunlight_tv)
    TextView mSunlightTv;

    @BindView(R.id.wind_power_tv)
    TextView mWindPowerTv;

    Context mContext;

    WeatherDataBean mWeatherData;

    FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity mHoursDataList;

    List<TextView> mTextViews = new ArrayList<>();

    public WeatherDataHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mTextViews.add(mAirTempTv);
        mTextViews.add(mRainfallTv);
        mTextViews.add(mSoilTempTv);
        mTextViews.add(mSoilHumidityTv);
        mTextViews.add(mAirHumidityTv);
        mTextViews.add(mSunlightTv);
        mTextViews.add(mWindPowerTv);
    }

    public void bindData(WeatherDataBean bean) {
        mWeatherData = bean;

        // 7天天气数据
        if (null != bean.dayWeatherList && !bean.dayWeatherList.isEmpty()) {
            for (int i = 0; i < bean.dayWeatherList.size(); i++) {
                FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity entity
                        = bean.dayWeatherList.get(i);
                if (DateUtils.isToday(entity.currDate)) {
                    float temp = Float.valueOf(entity.currTemp);
                    mTemperatureTv.setText(String.valueOf((int) temp));
                    mWeatherDescTv.setText(entity.weatherContent + " " + entity.tempBucket + " "
                            + entity.windPower);
                    mWeatherDateTv.setText(DateUtils.formatDate(entity.currDate, DateUtils.TIME_YYYY_MM_DD, DateUtils.TIME_YYYY_MM_DD_DOT) + " (" + entity.currLunarDate + ") " + entity.week);
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

    @OnClick({R.id.weather_data_layout, R.id.close_tips_iv, R.id.truth_data_btn,
            R.id.truth_data_setting_iv, R.id.air_temp_tv, R.id.rainfall_tv, R.id.soil_temp_tv,
            R.id.soil_humidity_tv, R.id.air_humidity_tv, R.id.sunlight_tv, R.id.wind_power_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weather_data_layout:
                ActivityNav.getInstance()
                        .startWeatherTrendActivity(mContext, mWeatherData.dayWeatherList);
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
            case R.id.air_humidity_tv: // 空气湿度
                setAirHumidityView();
                break;
            case R.id.sunlight_tv: // 日照
                setSunlightView();
                break;
            case R.id.wind_power_tv: // 风力
                setWindPowerView();
                break;
        }
    }

    private void setWindPowerView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity>
                    windPowerList = mHoursDataList.windPowerList;
            if (null != windPowerList && !windPowerList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                List<String> hours = new ArrayList<>();
                for (int i = 0; i < windPowerList.size(); i++) {
                    String value = windPowerList.get(i).value;
                    float temp;
                    if (TextUtils.isEmpty(value)) {
                        temp = 0;
                    } else {
                        temp = Float.valueOf(value);
                    }
                    temperatures.add((int) temp);
                    hours.add(windPowerList.get(i).hours);
                }
                mTruthDataView.setTemperatures(temperatures, hours);
                changeTextViewColor(mWindPowerTv);
            } else {
                ToastUtils.show("暂无数据");
            }
        } else {
            ToastUtils.show("暂无数据");
        }
    }

    private void setSunlightView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity>
                    sunshineList = mHoursDataList.sunshineList;
            if (null != sunshineList && !sunshineList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                List<String> hours = new ArrayList<>();
                for (int i = 0; i < sunshineList.size(); i++) {
                    String value = sunshineList.get(i).value;
                    float temp;
                    if (TextUtils.isEmpty(value)) {
                        temp = 0;
                    } else {
                        temp = Float.valueOf(value);
                    }
                    temperatures.add((int) temp);
                    hours.add(sunshineList.get(i).hours);
                }
                mTruthDataView.setTemperatures(temperatures, hours);
                changeTextViewColor(mSunlightTv);
            } else {
                ToastUtils.show("暂无数据");
            }
        } else {
            ToastUtils.show("暂无数据");
        }
    }

    private void setAirHumidityView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity>
                    airMoistureList = mHoursDataList.airMoistureList;
            if (null != airMoistureList && !airMoistureList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                List<String> hours = new ArrayList<>();
                for (int i = 0; i < airMoistureList.size(); i++) {
                    String value = airMoistureList.get(i).value;
                    float temp;
                    if (TextUtils.isEmpty(value)) {
                        temp = 0;
                    } else {
                        temp = Float.valueOf(value);
                    }
                    temperatures.add((int) temp);
                    hours.add(airMoistureList.get(i).hours);
                }
                mTruthDataView.setTemperatures(temperatures, hours);
                changeTextViewColor(mAirHumidityTv);
            } else {
                ToastUtils.show("暂无数据");
            }
        } else {
            ToastUtils.show("暂无数据");
        }
    }

    private void setSoilHumidityView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity>
                    soilMoistureList = mHoursDataList.soilMoistureList;
            if (null != soilMoistureList && !soilMoistureList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                List<String> hours = new ArrayList<>();
                for (int i = 0; i < soilMoistureList.size(); i++) {
                    String value = soilMoistureList.get(i).value;
                    float temp;
                    if (TextUtils.isEmpty(value)) {
                        temp = 0;
                    } else {
                        temp = Float.valueOf(value);
                    }
                    temperatures.add((int) temp);
                    hours.add(soilMoistureList.get(i).hours);
                }
                mTruthDataView.setTemperatures(temperatures, hours);
                changeTextViewColor(mSoilHumidityTv);
            } else {
                ToastUtils.show("暂无数据");
            }
        } else {
            ToastUtils.show("暂无数据");
        }
    }

    private void setSoilTempView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity>
                    soilTempList = mHoursDataList.soilTempLlist;
            if (null != soilTempList && !soilTempList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                List<String> hours = new ArrayList<>();
                for (int i = 0; i < soilTempList.size(); i++) {
                    String value = soilTempList.get(i).value;
                    float temp;
                    if (TextUtils.isEmpty(value)) {
                        temp = 0;
                    } else {
                        temp = Float.valueOf(value);
                    }
                    temperatures.add((int) temp);
                    hours.add(soilTempList.get(i).hours);
                }
                mTruthDataView.setTemperatures(temperatures, hours);
                changeTextViewColor(mSoilTempTv);
            } else {
                ToastUtils.show("暂无数据");
            }
        } else {
            ToastUtils.show("暂无数据");
        }
    }

    private void setRainfallView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity>
                    precipitationList = mHoursDataList.precipitationList;
            if (null != precipitationList && !precipitationList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                List<String> hours = new ArrayList<>();
                for (int i = 0; i < precipitationList.size(); i++) {
                    String value = precipitationList.get(i).value;
                    float temp;
                    if (TextUtils.isEmpty(value)) {
                        temp = 0;
                    } else {
                        temp = Float.valueOf(value);
                    }
                    temperatures.add((int) temp);
                    hours.add(precipitationList.get(i).hours);
                }
                mTruthDataView.setTemperatures(temperatures, hours);
                changeTextViewColor(mRainfallTv);
            } else {
                ToastUtils.show("暂无数据");
            }
        } else {
            ToastUtils.show("暂无数据");
        }
    }

    private void setTemperatureView() {
        if (null != mHoursDataList) {
            List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity>
                    airTempList = mHoursDataList.airTempList;
            if (null != airTempList && !airTempList.isEmpty()) {
                List<Integer> temperatures = new ArrayList<>();
                List<String> hours = new ArrayList<>();
                for (int i = 0; i < airTempList.size(); i++) {
                    String value = airTempList.get(i).value;
                    float temp;
                    if (TextUtils.isEmpty(value)) {
                        temp = 0;
                    } else {
                        temp = Float.valueOf(value);
                    }
                    temperatures.add((int) temp);
                    hours.add(airTempList.get(i).hours);
                }
                mTruthDataView.setTemperatures(temperatures, hours);
                changeTextViewColor(mAirTempTv);
            } else {
                ToastUtils.show("暂无数据");
            }
        } else {
            ToastUtils.show("暂无数据");
        }
    }

    private void changeTextViewColor(TextView textView) {
        for (int i = 0; i < mTextViews.size(); i ++) {
            TextView textView1 = mTextViews.get(i);
            if (textView1 == textView) {
                textView1.setTextColor(ContextCompat.getColor(itemView.getContext(), android.R.color.white));
            } else {
                textView1.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.gray_ccc));
            }
        }
    }
}
