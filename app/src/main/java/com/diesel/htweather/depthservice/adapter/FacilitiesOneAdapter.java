package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.FacilitiesOneHolder;
import com.diesel.htweather.depthservice.model.FacilitiesBean;

import java.util.List;

/**
 * Created by zhoujiangsen on 16/10/21.
 */
public class FacilitiesOneAdapter extends RecyclerView.Adapter<FacilitiesOneHolder> {

    List<FacilitiesBean> ownerSetList;

    LayoutInflater mInflater;

    public FacilitiesOneAdapter(Context context, List<FacilitiesBean> objects) {
        mInflater = LayoutInflater.from(context);
        ownerSetList = objects;
    }

    @Override
    public FacilitiesOneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FacilitiesOneHolder(mInflater.inflate(R.layout.facilities_item_one, parent, false));
    }

    @Override
    public void onBindViewHolder(FacilitiesOneHolder holder, int position) {
        FacilitiesBean bean = ownerSetList.get(position);
        holder.tvFacilities.setText(bean.getTitle());
        holder.tvFacilitiesTime.setText(bean.getSowingTime() + " _ " + bean.getPlantingTime());
        if ("1".equals(bean.getIsChecked())) {
            holder.rbFacilitiesSelect.setChecked(true);
        } else {
            holder.rbFacilitiesSelect.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return ownerSetList.size();
    }

    public List<FacilitiesBean> getOwnerSetList() {
        return ownerSetList;
    }
}
