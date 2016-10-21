package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.depthservice.holder.SearchHolder;
import com.diesel.htweather.depthservice.model.OnlineAdvisoryBean;
import com.diesel.htweather.util.PicassoUtils;

import java.util.List;

/**
 * Created by mac14 on 16/9/6.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {

    List<OnlineAdvisoryBean> mOnlineAdvisoryBeanList;
    Context mContext;


    public SearchAdapter(Context context, List<OnlineAdvisoryBean> onlineAdvisoryBeanList) {
        mContext = context;
        mOnlineAdvisoryBeanList = onlineAdvisoryBeanList;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_online_advisory, parent, false);
        return new SearchHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        OnlineAdvisoryBean advisoryBean = mOnlineAdvisoryBeanList.get(position);
        String userFacePath = Api.SERVER_URL + advisoryBean.getUserFace();
        PicassoUtils.loadImageViewHolder(mContext, userFacePath, R.drawable.ic_gather_data_avatar, holder.tvUserFace);
        holder.tvName.setText(advisoryBean.getUserNickName());
        holder.userType.setText(advisoryBean.getUserType());
        holder.locationAddr.setText(advisoryBean.getLocationAddr());
        holder.tvCreateTime.setText(advisoryBean.getCreatTime());
        holder.mContent.setText(advisoryBean.getContent());
        String imagePath[] = getImageViewUriPath(advisoryBean.getImgPaths());
        if (imagePath != null && imagePath.length == 3) {
            PicassoUtils.loadImageViewHolder(mContext, Api.SERVER_URL + imagePath[0], R.drawable.test_online_image, holder.tvImage1);
            PicassoUtils.loadImageViewHolder(mContext, Api.SERVER_URL + imagePath[1], R.drawable.test_online_image, holder.tvImage2);
            PicassoUtils.loadImageViewHolder(mContext, Api.SERVER_URL + imagePath[2], R.drawable.test_online_image, holder.tvImage3);
        }

        holder.tvUps.setText(advisoryBean.getUps());
        holder.tvComments.setText(advisoryBean.getComments());
        holder.tvReadCounts.setText(advisoryBean.getCounts());

    }

    @Override
    public int getItemCount() {
        return mOnlineAdvisoryBeanList.size();
    }

    public List<OnlineAdvisoryBean> getAdvisoryBeanList() {
        return mOnlineAdvisoryBeanList;
    }

    private String[] getImageViewUriPath(String path) {
        return path.split(";");
    }

}