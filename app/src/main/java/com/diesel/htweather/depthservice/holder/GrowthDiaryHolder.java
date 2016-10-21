package com.diesel.htweather.depthservice.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.RecyclerItemEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac14 on 16/9/6.
 */
public class GrowthDiaryHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvGrowthLunar)
    public TextView tvGrowthLunar;

    @BindView(R.id.tvGrowthTitle)
    public TextView tvGrowthTitle;

    @BindView(R.id.tvGrowthTime)
    public TextView tvGrowthTime;

    @BindView(R.id.tvGrowthReadNum)
    public TextView tvGrowthReadNum;

    public GrowthDiaryHolder(View itemView) {
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
