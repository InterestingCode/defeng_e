package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.depthservice.holder.OnlineAllMsgHolder;
import com.diesel.htweather.depthservice.model.OnlineAdvisoryBean;
import com.diesel.htweather.util.PicassoUtils;

import java.util.List;

/**
 * Created by mac14 on 16/9/6.
 */
public class OnlineAllMsgAdapter extends RecyclerView.Adapter<OnlineAllMsgHolder> {

    List<OnlineAdvisoryBean> mOnlineAdvisoryBeanList;
    Context mContext;


    public OnlineAllMsgAdapter(Context context, List<OnlineAdvisoryBean> onlineAdvisoryBeanList) {
        mContext = context;
        mOnlineAdvisoryBeanList = onlineAdvisoryBeanList;
    }

    @Override
    public OnlineAllMsgHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_online_advisory, parent, false);
        return new OnlineAllMsgHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OnlineAllMsgHolder holder, int position) {
        OnlineAdvisoryBean advisoryBean = mOnlineAdvisoryBeanList.get(position);
        String userFacePath = Api.SERVER_URL + advisoryBean.getUserFace();
        PicassoUtils.loadImageViewHolder(mContext, userFacePath, R.drawable.ic_gather_data_avatar, holder.tvUserFace);
        holder.tvName.setText(advisoryBean.getUserNickName());
        holder.userType.setText(advisoryBean.getUserType());
        holder.locationAddr.setText(advisoryBean.getLocationAddr());
        holder.tvCreateTime.setText(advisoryBean.getCreatTime());
        holder.mContent.setText(advisoryBean.getContent());
        String imagePath[] = getImageViewUriPath(advisoryBean.getImgPaths());
        if (imagePath.length > 0) {
            if (imagePath.length == 1) {
                PicassoUtils.loadImageViewHolder(mContext, Api.SERVER_URL + imagePath[0], R.drawable.test_online_image, holder.tvImage1);
                holder.tvImage2.setVisibility(View.INVISIBLE);
                holder.tvImage3.setVisibility(View.INVISIBLE);
            } else if (imagePath.length == 2) {
                PicassoUtils.loadImageViewHolder(mContext, Api.SERVER_URL + imagePath[0], R.drawable.test_online_image, holder.tvImage1);
                PicassoUtils.loadImageViewHolder(mContext, Api.SERVER_URL + imagePath[1], R.drawable.test_online_image, holder.tvImage2);
                holder.tvImage3.setVisibility(View.INVISIBLE);
            } else if (imagePath.length == 3) {
                PicassoUtils.loadImageViewHolder(mContext, Api.SERVER_URL + imagePath[0], R.drawable.test_online_image, holder.tvImage1);
                PicassoUtils.loadImageViewHolder(mContext, Api.SERVER_URL + imagePath[1], R.drawable.test_online_image, holder.tvImage2);
                PicassoUtils.loadImageViewHolder(mContext, Api.SERVER_URL + imagePath[2], R.drawable.test_online_image, holder.tvImage3);
            }
        }

        holder.tvUps.setText(advisoryBean.getUps());
        holder.tvComments.setText(advisoryBean.getComments());
        holder.tvReadCounts.setText(advisoryBean.getCounts());
        holder.itemView.setTag(advisoryBean);
    }

    @Override
    public int getItemCount() {
        return mOnlineAdvisoryBeanList.size();
    }

    public List<OnlineAdvisoryBean> getAdvisoryBeanList() {
        return mOnlineAdvisoryBeanList;
    }

    public void update(List<OnlineAdvisoryBean> list) {
        mOnlineAdvisoryBeanList = list;
        notifyDataSetChanged();
    }

    private String[] getImageViewUriPath(String path) {
        return path.split(";");
    }

}