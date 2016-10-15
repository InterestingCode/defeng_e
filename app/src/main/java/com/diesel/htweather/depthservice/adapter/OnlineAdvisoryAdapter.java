package com.diesel.htweather.depthservice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.OnlineAdvisoryHolder;
import com.diesel.htweather.depthservice.model.OnlineAdvisoryBean;

import java.util.List;

/**
 * Created by mac14 on 16/9/6.
 */
public class OnlineAdvisoryAdapter extends RecyclerView.Adapter<OnlineAdvisoryHolder> {

    List<OnlineAdvisoryBean> mOnlineAdvisoryBeanList;

    public OnlineAdvisoryAdapter(List<OnlineAdvisoryBean> onlineAdvisoryBeanList) {
        mOnlineAdvisoryBeanList = onlineAdvisoryBeanList;
    }

    @Override
    public OnlineAdvisoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_online_advisory, parent, false);
        return new OnlineAdvisoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OnlineAdvisoryHolder holder, int position) {
        OnlineAdvisoryBean advisoryBean = mOnlineAdvisoryBeanList.get(position);
        holder.tvName.setText(advisoryBean.getUserNickName());
        holder.userType.setText(advisoryBean.getUserType());
        holder.locationAddr.setText(advisoryBean.getLocationAddr());
        holder.tvCreateTime.setText(advisoryBean.getCreatTime());
        holder.mContent.setText(advisoryBean.getContent());
        holder.tvUps.setText(advisoryBean.getUps());
        holder.tvComments.setText(advisoryBean.getComments());
        holder.tvReadCounts.setText(advisoryBean.getCounts());

    }

    @Override
    public int getItemCount() {
        return mOnlineAdvisoryBeanList.size();
    }
}