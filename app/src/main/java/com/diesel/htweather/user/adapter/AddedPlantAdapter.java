package com.diesel.htweather.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.response.PlantCategoryResJO;
import com.diesel.htweather.user.holder.AddedPlantHolder;
import com.diesel.htweather.user.model.PlantBean;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/6
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class AddedPlantAdapter extends RecyclerView.Adapter<AddedPlantHolder> {

    private List<PlantCategoryResJO.CategoryEntity.CategoryListEntity> mAddedPlants;

    public AddedPlantAdapter(List<PlantCategoryResJO.CategoryEntity.CategoryListEntity> addedPlants) {
        mAddedPlants = addedPlants;
    }

    @Override
    public AddedPlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddedPlantHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_added_plant, parent, false));
    }

    @Override
    public void onBindViewHolder(AddedPlantHolder holder, int position) {
        holder.bindData(mAddedPlants.get(position));
    }

    @Override
    public int getItemCount() {
        return null == mAddedPlants ? 0 : mAddedPlants.size();
    }

}
