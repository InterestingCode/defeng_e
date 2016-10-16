package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.AddHotRcmdAreaEvent;
import com.diesel.htweather.response.LocationResJO;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/16
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class HotAndRcmdAreaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.plant_name_tv)
    TextView mPlantNameTv;

    LocationResJO.AreaListEntity.HotAreaListEntity mEntity;

    public HotAndRcmdAreaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new AddHotRcmdAreaEvent(mEntity));
            }
        });
    }

    public void binData(LocationResJO.AreaListEntity.HotAreaListEntity entity) {
        mEntity = entity;
        mPlantNameTv.setText(entity.arName);
    }
}
