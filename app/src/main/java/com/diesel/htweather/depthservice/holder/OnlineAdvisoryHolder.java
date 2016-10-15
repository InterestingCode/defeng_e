package com.diesel.htweather.depthservice.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.RecyclerItemEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mac14 on 16/9/6.
 */
public class OnlineAdvisoryHolder extends RecyclerView.ViewHolder {

    public ImageView tvUserFace;
    public TextView tvName;
    public TextView userType;
    public TextView locationAddr;
    public TextView tvCreateTime;
    public TextView mContent;
    public ImageView tvImage1;
    public ImageView tvImage2;
    public ImageView tvImage3;
    public TextView tvUps;
    public TextView tvComments;
    public TextView tvReadCounts;
    public ImageView tvUpsBtn;
    public ImageView tvCommentsBtn;


    public OnlineAdvisoryHolder(View itemView) {
        super(itemView);
        tvUserFace = (ImageView) itemView.findViewById(R.id.tvUserFace);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        userType = (TextView) itemView.findViewById(R.id.userType);
        locationAddr = (TextView) itemView.findViewById(R.id.locationAddr);
        tvCreateTime = (TextView) itemView.findViewById(R.id.tvCreateTime);
        mContent = (TextView) itemView.findViewById(R.id.tvContent);
        tvImage1 = (ImageView) itemView.findViewById(R.id.tvImage1);
        tvImage2 = (ImageView) itemView.findViewById(R.id.tvImage2);
        tvImage3 = (ImageView) itemView.findViewById(R.id.tvImage3);
        tvUps = (TextView) itemView.findViewById(R.id.tvUps);
        tvComments = (TextView) itemView.findViewById(R.id.tvComments);
        tvReadCounts = (TextView) itemView.findViewById(R.id.tvReadCounts);
        tvUpsBtn = (ImageView) itemView.findViewById(R.id.tvUpsBtn);
        tvCommentsBtn = (ImageView) itemView.findViewById(R.id.tvCommentsBtn);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new RecyclerItemEvent(getLayoutPosition()));
            }
        });
    }
}
