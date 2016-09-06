package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.DepthDiaryHolder;
import com.diesel.htweather.depthservice.model.GrowthDiaryBean;

import java.util.List;

/**
 * Created by penley on 16/9/6.
 */
public class DepthDiaryAdapter extends BaseArrayAdapter<GrowthDiaryBean, DepthDiaryHolder> {
    public DepthDiaryAdapter(Context context, List<GrowthDiaryBean> objects) {
        super(context, R.layout.list_item_growth_diary, objects);
    }

    @Override
    protected DepthDiaryHolder createHolder() {
        return new DepthDiaryHolder();
    }

    @Override
    protected void initHolder(int position, View v, DepthDiaryHolder holder) {

    }

    @Override
    protected void initDefaultHolder(int position, DepthDiaryHolder holder, GrowthDiaryBean item) {

    }

    @Override
    protected void bundleValue(int position, DepthDiaryHolder holder, GrowthDiaryBean item) {

    }
}
