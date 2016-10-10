package com.diesel.htweather.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.AddCropsEvent;
import com.diesel.htweather.response.PlantCategoryResJO;

import org.greenrobot.eventbus.EventBus;

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

    PlantCategoryResJO.CategoryEntity.CategoryListEntity mEntity;

    public AddCropsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new AddCropsEvent(mEntity));
            }
        });
    }

    public void binData(PlantCategoryResJO.CategoryEntity.CategoryListEntity entity) {
        mEntity = entity;
        mPlantNameTv.setText(entity.name);
        itemView.setBackgroundResource(
                entity.isSelected ? R.drawable.shape_white_bg_with_radius_and_yellow_stroke
                        : R.drawable.shape_white_bg_with_radius_and_stroke);
    }
}
