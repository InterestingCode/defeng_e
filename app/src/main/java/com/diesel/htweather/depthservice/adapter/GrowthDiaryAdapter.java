package com.diesel.htweather.depthservice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.GrowthDiaryHolder;

/**
 * Created by mac14 on 16/9/6.
 */
public class GrowthDiaryAdapter extends RecyclerView.Adapter<GrowthDiaryHolder> {
    @Override
    public GrowthDiaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GrowthDiaryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_growth_diary, parent, false));
    }

    @Override
    public void onBindViewHolder(GrowthDiaryHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
