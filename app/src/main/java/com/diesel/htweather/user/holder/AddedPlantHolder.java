package com.diesel.htweather.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.DeletePlantEvent;
import com.diesel.htweather.response.PlantCategoryResJO;
import com.diesel.htweather.user.model.PlantBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
public class AddedPlantHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.plant_name_tv)
    TextView mPlantNameTv;

    public AddedPlantHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(PlantCategoryResJO.CategoryEntity.CategoryListEntity bean) {
        mPlantNameTv.setText(bean.name);
    }

    @OnClick(R.id.delete_btn)
    public void onClick() {
        EventBus.getDefault().post(new DeletePlantEvent(getAdapterPosition()));
    }
}
