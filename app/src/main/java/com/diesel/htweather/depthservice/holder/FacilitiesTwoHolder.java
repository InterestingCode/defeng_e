package com.diesel.htweather.depthservice.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.DepthItemEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhoujiangsen on 16/10/21.
 */
public class FacilitiesTwoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ll_item_bg)
    public LinearLayout ll_item_bg;

    @BindView(R.id.tvFacilitiesTitle)
    public TextView tvFacilitiesTitle;

    public FacilitiesTwoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DepthItemEvent(getLayoutPosition() - 1));
            }
        });
    }
}
