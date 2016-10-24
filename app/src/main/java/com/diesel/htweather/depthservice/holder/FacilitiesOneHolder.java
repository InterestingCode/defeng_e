package com.diesel.htweather.depthservice.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.RecyclerItemEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhoujiangsen on 16/10/21.
 */
public class FacilitiesOneHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvFacilities)
    public TextView tvFacilities;

    @BindView(R.id.tvFacilitiesTime)
    public TextView tvFacilitiesTime;

    @BindView(R.id.rl_item_bg)
    public RelativeLayout rl_item_bg;

    public FacilitiesOneHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new RecyclerItemEvent(getLayoutPosition() - 1));
            }
        });
    }
}
