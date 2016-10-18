package com.diesel.htweather.farming.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    public WeatherDataHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void bindData(WeatherDataBean bean) {
        List<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> dayWeatherList = bean.dayWeatherList;
        if (null != bean.dayWeatherList && !bean.dayWeatherList.isEmpty()) {
            for (int i = 0; i < bean.dayWeatherList.size(); i ++) {
                FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity entity = bean.dayWeatherList.get(i);
                if (DateUtils.isToday(entity.currDate)) {
                    mTemperatureTv.setText(Integer.valueOf(entity.currTemp));
                    break;
                }
            }
        }

        Integer[] temp = new Integer[]{32, 33, 33, 35, 35, 36, 35, 37, 39, 38, 36, 33};
        List<Integer> temperatures = Arrays.asList(temp);
        mTruthDataView.setTemperatures(temperatures);

//        mTemperatureTv.setText(DataProcessUtils.getTemperatureWithouUnit(bean.currTemp));
    }

    @OnClick({R.id.more_btn, R.id.close_tips_iv, R.id.truth_data_btn, R.id.truth_data_setting_iv})
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
        }
    }
}
