package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.event.AddFocusAreaEvent;
import com.diesel.htweather.event.AddPlantAndAreaEvent;
import com.diesel.htweather.user.model.AddPlantBean;
import com.diesel.htweather.user.model.PlantBaseBean;

import org.greenrobot.eventbus.EventBus;

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
public class AddFocusAreaHolder extends RecyclerView.ViewHolder {

    View mView;

    public AddFocusAreaHolder(final View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new AddFocusAreaEvent());
            }
        });
    }

    public void bindData(AddPlantBean bean) {
        mView.setVisibility(
                bean.showStatus == PlantBaseBean.SHOW_STATUS_LIST ? View.VISIBLE : View.GONE);
    }
}
