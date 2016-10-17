package com.diesel.htweather.depthservice.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.event.PublishIssuesEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/11
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class AddPublishIssuesHolder extends RecyclerView.ViewHolder {

    public AddPublishIssuesHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new PublishIssuesEvent(PublishIssuesEvent.ACTION_ADD_PHOTO, getLayoutPosition()));
            }
        });
    }
}
