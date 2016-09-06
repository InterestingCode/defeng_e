package com.diesel.htweather.depthservice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.OnlineAdvisoryHolder;

/**
 * Created by mac14 on 16/9/6.
 */
public class OnlineAdvisoryAdapter extends RecyclerView.Adapter<OnlineAdvisoryHolder> {
    @Override
    public OnlineAdvisoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OnlineAdvisoryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_online_advisory, parent, false));
    }

    @Override
    public void onBindViewHolder(OnlineAdvisoryHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}