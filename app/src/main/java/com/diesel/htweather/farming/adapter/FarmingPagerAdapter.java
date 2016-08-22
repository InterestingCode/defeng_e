package com.diesel.htweather.farming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.farming.holder.WeatherDataHolder;
import com.diesel.htweather.farming.model.ActualFarmBean;
import com.diesel.htweather.farming.model.FarmBannerBean;
import com.diesel.htweather.farming.model.FarmInfoBean;
import com.diesel.htweather.farming.model.FarmPolicyBean;
import com.diesel.htweather.farming.model.WeatherDataBean;

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
public class FarmingPagerAdapter extends RecyclerView.Adapter {

    private static final int TYPE_WEATHER_DATA = 1;

    private static final int TYPE_ACTUAL_FARM = 2;

    private static final int TYPE_FARM_INFO = 3;

    private static final int TYPE_FARM_POLICY = 4;

    private static final int TYPE_FARM_BANNER = 5;

    private List<BaseBean> mFarmingData;

    public FarmingPagerAdapter(List<BaseBean> farmingData) {
        mFarmingData = farmingData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case TYPE_WEATHER_DATA:
                holder = new WeatherDataHolder(
                        inflater.inflate(R.layout.list_item_weather_data, parent, false));
                break;
            case TYPE_ACTUAL_FARM:
                holder = new WeatherDataHolder(
                        inflater.inflate(R.layout.list_item_weather_data, parent, false));
                break;
            case TYPE_FARM_INFO:
                holder = new WeatherDataHolder(
                        inflater.inflate(R.layout.list_item_weather_data, parent, false));
                break;
            case TYPE_FARM_POLICY:
                holder = new WeatherDataHolder(
                        inflater.inflate(R.layout.list_item_weather_data, parent, false));
                break;
            case TYPE_FARM_BANNER:
                holder = new WeatherDataHolder(
                        inflater.inflate(R.layout.list_item_weather_data, parent, false));
                break;
            default:
                View emptyView = new View(parent.getContext());
                holder = new RecyclerView.ViewHolder(emptyView) {
                };
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mFarmingData == null ? 0 : mFarmingData.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        BaseBean bean = mFarmingData.get(position);
        if (bean instanceof WeatherDataBean) {
            viewType = TYPE_WEATHER_DATA;
        } else if (bean instanceof ActualFarmBean) {
            viewType = TYPE_ACTUAL_FARM;
        } else if (bean instanceof FarmInfoBean) {
            viewType = TYPE_FARM_INFO;
        } else if (bean instanceof FarmPolicyBean) {
            viewType = TYPE_FARM_POLICY;
        } else if (bean instanceof FarmBannerBean) {
            viewType = TYPE_FARM_BANNER;
        }
        return viewType;
    }
}
