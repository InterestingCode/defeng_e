package com.diesel.htweather.depthservice.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.event.RecyclerItemEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mac14 on 16/9/6.
 */
public class GrowthDiaryHolder  extends RecyclerView.ViewHolder{
    public GrowthDiaryHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new RecyclerItemEvent(getLayoutPosition()));
            }
        });
    }
}
