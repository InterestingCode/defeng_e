package com.diesel.htweather.depthservice.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.RecyclerItemEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac14 on 16/9/6.
 */
public class OnlineAdvisoryHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvUserFace)
    public ImageView tvUserFace;

    @BindView(R.id.tvName)
    public TextView tvName;

    @BindView(R.id.userType)
    public TextView userType;

    @BindView(R.id.locationAddr)
    public TextView locationAddr;

    @BindView(R.id.tvCreateTime)
    public TextView tvCreateTime;

    @BindView(R.id.tvContent)
    public TextView mContent;

    @BindView(R.id.tvImage1)
    public ImageView tvImage1;

    @BindView(R.id.tvImage2)
    public ImageView tvImage2;

    @BindView(R.id.tvImage3)
    public ImageView tvImage3;

    @BindView(R.id.tvUps)
    public TextView tvUps;

    @BindView(R.id.tvComments)
    public TextView tvComments;

    @BindView(R.id.tvReadCounts)
    public TextView tvReadCounts;

    @BindView(R.id.tvUpsBtn)
    public ImageView tvUpsBtn;

    @BindView(R.id.tvCommentsBtn)
    public ImageView tvCommentsBtn;


    public OnlineAdvisoryHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new RecyclerItemEvent(getLayoutPosition()));
            }
        });
    }


}
