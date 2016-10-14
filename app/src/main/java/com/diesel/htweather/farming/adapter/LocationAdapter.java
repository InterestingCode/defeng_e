package com.diesel.htweather.farming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.farming.holder.HotAreaHolder;
import com.diesel.htweather.farming.holder.LocationAreaHolder;
import com.diesel.htweather.farming.holder.RecommendAreaHolder;
import com.diesel.htweather.farming.model.HotAreaBean;
import com.diesel.htweather.farming.model.LocationBean;
import com.diesel.htweather.farming.model.RecommendAreaBean;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/10/13
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class LocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BaseBean> mAreaList;

    public LocationAdapter(List<BaseBean> areaList) {
        mAreaList = areaList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder;
        if (viewType == 1) {
            holder = new LocationAreaHolder(inflater.inflate(R.layout.list_item_location_loc, parent, false));
        } else if (viewType == 2) {
            holder = new HotAreaHolder(inflater.inflate(R.layout.list_item_location_hot, parent, false));
        } else if (viewType == 3) {
            holder = new RecommendAreaHolder(inflater.inflate(R.layout.list_item_location_recommend, parent, false));
        } else {
            View emptyView = new View(parent.getContext());
            holder = new RecyclerView.ViewHolder(emptyView) {
            };
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        BaseBean bean = mAreaList.get(position);
        if (viewType == 1) {
            ((LocationAreaHolder) holder).bindData((LocationBean) bean);
        } else if (viewType == 2) {
            ((HotAreaHolder) holder).bindData((HotAreaBean) bean);
        } else if (viewType == 3) {
            ((RecommendAreaHolder) holder).bindData((RecommendAreaBean) bean);
        }
    }

    @Override
    public int getItemCount() {
        return null == mAreaList ? 0 : mAreaList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        BaseBean bean = mAreaList.get(position);
        if (bean instanceof LocationBean) {
            viewType = 1;
        } else if (bean instanceof HotAreaBean) {
            viewType = 2;
        } else if (bean instanceof RecommendAreaBean) {
            viewType = 3;
        }
        return viewType;
    }
}
