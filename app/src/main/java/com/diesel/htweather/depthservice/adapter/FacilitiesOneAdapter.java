package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.FacilitiesOneHolder;
import com.diesel.htweather.depthservice.model.FacilitiesBean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhoujiangsen on 16/10/21.
 */
public class FacilitiesOneAdapter extends RecyclerView.Adapter<FacilitiesOneHolder> {

    List<FacilitiesBean> ownerSetList;

    LayoutInflater mInflater;

    Context mContext;

    public FacilitiesOneAdapter(Context context, List<FacilitiesBean> objects) {
        mInflater = LayoutInflater.from(context);
        ownerSetList = sortList(objects);
        mContext = context;
    }

    @Override
    public FacilitiesOneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FacilitiesOneHolder(mInflater.inflate(R.layout.facilities_item_one, parent, false));
    }

    @Override
    public void onBindViewHolder(FacilitiesOneHolder holder, int position) {
        FacilitiesBean bean = ownerSetList.get(position);
        holder.tvFacilities.setText(bean.getTitle());
        holder.tvFacilitiesTime.setText(bean.getSowingTime() + " - " + bean.getPlantingTime());
        if ("1".equals(bean.getIsChecked())) {
            holder.rl_item_bg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.polyline_blue_point_color));
        } else {
            holder.rl_item_bg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.txt_color_header_title));
        }
    }

    @Override
    public int getItemCount() {
        return ownerSetList.size();
    }

    public List<FacilitiesBean> getOwnerSetList() {
        return ownerSetList;
    }

    private List<FacilitiesBean> sortList(List<FacilitiesBean> list) {
        // 按点击数倒序
        Collections.sort(list, new Comparator<FacilitiesBean>() {
            public int compare(FacilitiesBean arg0, FacilitiesBean arg1) {
                int hits0 = Integer.valueOf(arg0.getIsChecked());
                int hits1 = Integer.valueOf(arg1.getIsChecked());
                if (hits0 > hits1) {
                    return 1;
                } else if (hits1 == hits0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return list;
    }
}
