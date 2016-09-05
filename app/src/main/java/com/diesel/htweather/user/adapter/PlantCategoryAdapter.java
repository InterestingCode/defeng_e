package com.diesel.htweather.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.user.holder.PlantCategoryHolder;
import com.diesel.htweather.user.model.PlantCategoryBean;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/5
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class PlantCategoryAdapter extends RecyclerView.Adapter<PlantCategoryHolder> {

    private List<PlantCategoryBean> mCategories;

    public PlantCategoryAdapter(List<PlantCategoryBean> categories) {
        mCategories = categories;
    }

    @Override
    public PlantCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlantCategoryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_plant_category, parent, false));
    }

    @Override
    public void onBindViewHolder(PlantCategoryHolder holder, int position) {
        holder.bindData(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return null == mCategories ? 0 : mCategories.size();
    }
}
