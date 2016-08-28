package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.util.ActivityNav;

import org.greenrobot.eventbus.EventBus;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/27
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class InnerFarmingPolicyHolder extends RecyclerView.ViewHolder {

    public InnerFarmingPolicyHolder(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new RecyclerItemEvent(getLayoutPosition()));
            }
        });
    }
}
