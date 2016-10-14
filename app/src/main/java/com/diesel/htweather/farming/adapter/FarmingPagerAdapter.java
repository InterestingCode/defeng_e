package com.diesel.htweather.farming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.farming.holder.ActualFarmingHolder;
import com.diesel.htweather.farming.holder.FarmingBannerHolder;
import com.diesel.htweather.farming.holder.FarmingInfoHolder;
import com.diesel.htweather.farming.holder.FarmingPolicyHolder;
import com.diesel.htweather.farming.holder.WeatherDataHolder;
import com.diesel.htweather.farming.holder.WeatherPageHeaderHolder;
import com.diesel.htweather.farming.model.ActualFarmingBean;
import com.diesel.htweather.farming.model.AdvertiseBannerBean;
import com.diesel.htweather.farming.model.FarmingInfoBean;
import com.diesel.htweather.farming.model.FarmingPolicyBean;
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

    private static final int TYPE_FARMING_INFO = 3;

    private static final int TYPE_FARMING_POLICY = 4;

    private static final int TYPE_FARMING_BANNER = 5;

    private static final int TYPE_PAGE_HEADER = 6;

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
                holder = new ActualFarmingHolder(
                        inflater.inflate(R.layout.list_item_actual_farming, parent, false));
                break;
            case TYPE_FARMING_INFO:
                holder = new FarmingInfoHolder(
                        inflater.inflate(R.layout.list_item_farming_info, parent, false));
                break;
            case TYPE_FARMING_POLICY:
                holder = new FarmingPolicyHolder(
                        inflater.inflate(R.layout.list_item_farming_policy, parent, false));
                break;
            case TYPE_FARMING_BANNER:
                holder = new FarmingBannerHolder(
                        inflater.inflate(R.layout.list_item_farming_banner, parent, false));
                break;
            case TYPE_PAGE_HEADER:
                holder = new WeatherPageHeaderHolder(
                        inflater.inflate(R.layout.weather_page_header_layout, parent, false));
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
        BaseBean bean = mFarmingData.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_WEATHER_DATA:
                ((WeatherDataHolder) holder).bindData((WeatherDataBean) bean);
                break;
            case TYPE_ACTUAL_FARM:
                ((ActualFarmingHolder) holder).bindData((ActualFarmingBean) bean);
                break;
            case TYPE_FARMING_INFO:
                ((FarmingInfoHolder) holder).bindData((FarmingInfoBean) bean);
                break;
            case TYPE_FARMING_POLICY:
                ((FarmingPolicyHolder) holder).bindData((FarmingPolicyBean) bean);
                break;
            case TYPE_FARMING_BANNER:
                ((FarmingBannerHolder) holder).bindData();
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mFarmingData == null ? 0 : mFarmingData.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        BaseBean bean = mFarmingData.get(position);
        if (bean instanceof WeatherDataBean) {
            viewType = TYPE_WEATHER_DATA;
        } else if (bean instanceof ActualFarmingBean) {
            viewType = TYPE_ACTUAL_FARM;
        } else if (bean instanceof FarmingInfoBean) {
            viewType = TYPE_FARMING_INFO;
        } else if (bean instanceof FarmingPolicyBean) {
            viewType = TYPE_FARMING_POLICY;
        } else if (bean instanceof AdvertiseBannerBean) {
            viewType = TYPE_FARMING_BANNER;
        } else {
            viewType = TYPE_PAGE_HEADER;
        }
        return viewType;
    }
}
