package com.diesel.htweather.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.response.PlantCategoryResJO;
import com.diesel.htweather.user.holder.AddCropsHolder;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/9
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class AddCropsAdapter extends RecyclerView.Adapter<AddCropsHolder> {

    private List<PlantCategoryResJO.CategoryEntity.CategoryListEntity> mCropList;

    public void setData(List<PlantCategoryResJO.CategoryEntity.CategoryListEntity> baseCropList) {
        mCropList = baseCropList;
        notifyDataSetChanged();
    }

    @Override
    public AddCropsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddCropsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_plant_name, parent, false));
    }

    @Override
    public void onBindViewHolder(AddCropsHolder holder, int position) {
        holder.binData(mCropList.get(position));
    }

    @Override
    public int getItemCount() {
        return null != mCropList ? mCropList.size() : 0;
    }
}
