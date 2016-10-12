package com.diesel.htweather.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.event.ProblemPhotoEvent;

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
public class AddPhotoHolder extends RecyclerView.ViewHolder {

    public AddPhotoHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ProblemPhotoEvent(ProblemPhotoEvent.ACTION_ADD_PHOTO, getLayoutPosition()));
            }
        });
    }
}
