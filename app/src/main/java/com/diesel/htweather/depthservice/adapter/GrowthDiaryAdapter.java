package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.GrowthDiaryHolder;
import com.diesel.htweather.depthservice.model.GrowthDiaryBean;

import java.util.List;

/**
 * Created by mac14 on 16/9/6.
 */
public class GrowthDiaryAdapter extends RecyclerView.Adapter<GrowthDiaryHolder> {

    List<GrowthDiaryBean> mGrowthDiaryBeanList;

    LayoutInflater mInflater;

    public GrowthDiaryAdapter(Context context, List<GrowthDiaryBean> objects) {
        mInflater = LayoutInflater.from(context);
        mGrowthDiaryBeanList = objects;
    }

    @Override
    public GrowthDiaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GrowthDiaryHolder(mInflater.inflate(R.layout.list_item_growth_diary, parent, false));
    }

    @Override
    public void onBindViewHolder(GrowthDiaryHolder holder, int position) {
        GrowthDiaryBean bean = mGrowthDiaryBeanList.get(position);
        holder.tvGrowthLunar.setText(bean.getLunarStr());
        holder.tvGrowthTitle.setText(bean.getTitle());
        holder.tvGrowthTime.setText(bean.getTime());
        holder.tvGrowthReadNum.setText("阅读 " + bean.getReadCount());
    }

    @Override
    public int getItemCount() {
        return mGrowthDiaryBeanList.size();
    }

    public List<GrowthDiaryBean> getGrowthDiaryBeanList() {
        return mGrowthDiaryBeanList;
    }
}
