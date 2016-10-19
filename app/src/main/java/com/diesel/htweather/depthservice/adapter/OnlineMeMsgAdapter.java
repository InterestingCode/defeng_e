package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.depthservice.holder.OnlineMeMsgHolder;
import com.diesel.htweather.depthservice.model.OnlineAdvisoryBean;
import com.diesel.htweather.util.PicassoUtils;

import java.util.List;

/**
 * Created by mac14 on 16/9/6.
 */
public class OnlineMeMsgAdapter extends RecyclerView.Adapter<OnlineMeMsgHolder> {

    List<OnlineAdvisoryBean> mOnlineAdvisoryBeanList;
    Context mContext;

    public OnlineMeMsgAdapter(Context context, List<OnlineAdvisoryBean> onlineAdvisoryBeanList) {
        mContext = context;
        mOnlineAdvisoryBeanList = onlineAdvisoryBeanList;
    }

    @Override
    public OnlineMeMsgHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_online_advisory, parent, false);
        return new OnlineMeMsgHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OnlineMeMsgHolder holder, int position) {
        OnlineAdvisoryBean advisoryBean = mOnlineAdvisoryBeanList.get(position);
        String userFacePath = Api.SERVER_URL + advisoryBean.getUserFace();
        PicassoUtils.loadImageViewHolder(mContext, userFacePath, R.drawable.ic_gather_data_avatar, holder.tvUserFace);
        holder.tvName.setText(advisoryBean.getUserNickName());
        holder.userType.setText(advisoryBean.getUserType());
        holder.locationAddr.setText(advisoryBean.getLocationAddr());
        holder.tvCreateTime.setText(advisoryBean.getCreatTime());
        holder.mContent.setText(advisoryBean.getContent());
        String imagePath[] = getImageViewUriPath(advisoryBean.getImgPaths());
        // TODO 下载图片
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

//    private void setImageViewBitmap(final ImageView imageView, String imageUrl) {
//        DepthWebService.getInstance().getNetworkBitmap(imageUrl, new BitmapCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.e(TAG, "getNetworkBitmap#onError() " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Bitmap response, int id) {
//                Log.v(TAG, "getNetworkBitmap#onResponse() " + "缓存图片成功");
//                imageView.setImageBitmap(response);
//            }
//        });
//    }

}