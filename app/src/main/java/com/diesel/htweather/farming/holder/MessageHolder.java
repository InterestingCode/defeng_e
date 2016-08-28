package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.util.ActivityNav;

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
public class MessageHolder extends RecyclerView.ViewHolder {

    public MessageHolder(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityNav.getInstance().startMessageDetailsActivity(itemView.getContext());
            }
        });
    }
}
