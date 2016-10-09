package com.diesel.htweather.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.response.PlantCategoryResJO;

import butterknife.BindView;
import butterknife.ButterKnife;

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
public class AddCropsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.plant_name_tv)
    TextView mPlantNameTv;

    public AddCropsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void binData(PlantCategoryResJO.CategoryEntity.CategoryListEntity entity) {
        mPlantNameTv.setText(entity.name);
    }
}
