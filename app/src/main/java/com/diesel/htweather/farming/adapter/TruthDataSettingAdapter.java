package com.diesel.htweather.farming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.holder.TruthDataSettingHolder;
import com.diesel.htweather.farming.model.TruthDataBean;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/28
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class TruthDataSettingAdapter extends RecyclerView.Adapter<TruthDataSettingHolder> {

    private List<TruthDataBean> mTruthData;

    public TruthDataSettingAdapter(List<TruthDataBean> truthData) {
        mTruthData = truthData;
    }

    @Override
    public TruthDataSettingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TruthDataSettingHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_setting_truth_data, parent, false));
    }

    @Override
    public void onBindViewHolder(TruthDataSettingHolder holder, int position) {
        holder.bindData(mTruthData.get(position));
    }

    @Override
    public int getItemCount() {
        return mTruthData == null ? 0 : mTruthData.size();
    }
}
