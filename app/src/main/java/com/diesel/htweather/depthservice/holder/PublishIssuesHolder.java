package com.diesel.htweather.depthservice.holder;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.event.PublishIssuesEvent;
import com.diesel.htweather.util.BitmapUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.BitmapCallback;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;

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
public class PublishIssuesHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.problem_photo_view)
    SimpleDraweeView mProblemPhotoView;

    public PublishIssuesHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(String imagePath) {
        String path = Api.SERVER_URL + imagePath;
        setImageViewBitmap(mProblemPhotoView, path);

    }

    @OnClick(R.id.delete_photo_btn)
    public void onClick() {
        EventBus.getDefault().post(new PublishIssuesEvent(PublishIssuesEvent.ACTION_DEL_PHOTO, getLayoutPosition()));
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
                imageView.setImageDrawable(BitmapUtils.resizeImage(response, 270, 270));
            }
        });
    }
}
