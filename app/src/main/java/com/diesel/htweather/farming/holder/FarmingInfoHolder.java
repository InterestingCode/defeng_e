package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.farming.FarmingPolicyActivity;
import com.diesel.htweather.util.ActivityNav;

import org.greenrobot.eventbus.EventBus;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/8/24
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */
public class FarmingInfoHolder extends RecyclerView.ViewHolder {

    public FarmingInfoHolder(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityNav.getInstance().startFarmingPolicyActivity(itemView.getContext(),
                        FarmingPolicyActivity.TYPE_FARMING_INFO);
            }
        });
    }
}
