package com.diesel.htweather.depthservice.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.OnlineMeMsgHolder;
import com.diesel.htweather.depthservice.model.OnlineAdvisoryBean;
import com.diesel.htweather.webapi.DepthWebService;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;

/**
 * Created by mac14 on 16/9/6.
 */
public class OnlineMeMsgAdapter extends RecyclerView.Adapter<OnlineMeMsgHolder> {

    List<OnlineAdvisoryBean> mOnlineAdvisoryBeanList;

    public OnlineMeMsgAdapter(List<OnlineAdvisoryBean> onlineAdvisoryBeanList) {
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
//        setImageViewBitmap(holder.tvUserFace, Api.SERVER_URL + advisoryBean.getUserFace());
        holder.tvName.setText(advisoryBean.getUserNickName());
        holder.userType.setText(advisoryBean.getUserType());
        holder.locationAddr.setText(advisoryBean.getLocationAddr());
        holder.tvCreateTime.setText(advisoryBean.getCreatTime());
        holder.mContent.setText(advisoryBean.getContent());
        String imagePath[] = getImageViewUriPath(advisoryBean.getImgPaths());
        // TODO 下载图片
//        if (imagePath != null && imagePath.length >= 3) {
//            setImageViewBitmap(holder.tvImage1, Api.SERVER_URL + imagePath[0]);
//            setImageViewBitmap(holder.tvImage2, Api.SERVER_URL + imagePath[1]);
//            setImageViewBitmap(holder.tvImage3, Api.SERVER_URL + imagePath[2]);
//        }

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

    private void setImageViewBitmap(final ImageView imageView, String imageUrl) {
        DepthWebService.getInstance().getNetworkBitmap(imageUrl, new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getNetworkBitmap#onError() " + e.getMessage());
            }

            @Override
            public void onResponse(Bitmap response, int id) {
                Log.v(TAG, "getNetworkBitmap#onResponse() " + "缓存图片成功");
                imageView.setImageBitmap(response);
            }
        });
    }

}