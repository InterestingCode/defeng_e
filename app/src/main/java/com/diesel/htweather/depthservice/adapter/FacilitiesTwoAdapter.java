package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.FacilitiesTwoHolder;
import com.diesel.htweather.depthservice.model.FacilitiesBean;

import java.util.List;

/**
 * Created by zhoujiangsen on 16/10/21.
 */
public class FacilitiesTwoAdapter extends RecyclerView.Adapter<FacilitiesTwoHolder> {

    List<FacilitiesBean> recommendSetList;

    LayoutInflater mInflater;

    public FacilitiesTwoAdapter(Context context, List<FacilitiesBean> objects) {
        mInflater = LayoutInflater.from(context);
        recommendSetList = objects;
    }

    @Override
    public FacilitiesTwoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FacilitiesTwoHolder(mInflater.inflate(R.layout.facilities_item_two, parent, false));
    }

    @Override
    public void onBindViewHolder(FacilitiesTwoHolder holder, int position) {
        FacilitiesBean bean = recommendSetList.get(position);
        holder.tvFacilitiesTitle.setText(bean.getTitle());
        // TODO 选中设置
    }

    @Override
    public int getItemCount() {
        return recommendSetList.size();
    }

}
