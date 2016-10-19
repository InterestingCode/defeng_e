package com.diesel.htweather.depthservice.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.event.PublishIssuesEvent;
import com.diesel.htweather.util.PicassoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    Context mContext;

    public PublishIssuesHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    public void bindData(String imagePath) {
        String path = Api.SERVER_URL + imagePath;
        PicassoUtils.loadImageViewSize(mContext, path, 270, 270, mProblemPhotoView);
    }

    @OnClick(R.id.delete_photo_btn)
    public void onClick() {
        EventBus.getDefault().post(new PublishIssuesEvent(PublishIssuesEvent.ACTION_DEL_PHOTO, getLayoutPosition()));
    }
}
