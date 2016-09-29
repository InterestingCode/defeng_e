package com.diesel.htweather.user.holder;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.DeletePlantAndAreaEvent;
import com.diesel.htweather.user.model.PlantAndAreaBean;
import com.diesel.htweather.user.model.PlantBaseBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/4
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class PlantAndAreaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.plant_name_tv)
    TextView mPlantNameTv;

    @BindView(R.id.plant_area_tv)
    TextView mPlantAreaTv;

    @BindView(R.id.delete_btn)
    ImageView mDeleteBtn;

    Resources mResources;

    private PlantAndAreaBean mBean;

    public PlantAndAreaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mResources = itemView.getResources();
    }

    public void bindData(PlantAndAreaBean bean) {
        mBean = bean;
        mPlantNameTv.setText(mResources.getString(R.string.plants_name_with_args, bean.plantName));
        mPlantAreaTv.setText(mResources.getString(R.string.plants_area_with_args, bean.plantArea));
        mDeleteBtn.setVisibility(bean.showStatus == PlantBaseBean.SHOW_STATUS_DELETE ? View.VISIBLE
                : View.INVISIBLE);
    }

    @OnClick(R.id.delete_btn)
    public void deletePlant() {
        EventBus.getDefault().post(new DeletePlantAndAreaEvent(getAdapterPosition(), mBean));
    }
}
