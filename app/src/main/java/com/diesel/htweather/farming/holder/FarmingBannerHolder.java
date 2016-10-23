package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.DeleteBannerEvent;
import com.diesel.htweather.farming.model.ActivityBannerBean;
import com.diesel.htweather.farming.model.AdvertiseBannerBean;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.util.ActivityNav;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/8/24
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class FarmingBannerHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.farming_banner_iv)
    SimpleDraweeView mBannerIv;

    private String mLinkUrl;

    private AdvertiseBannerBean mAdvertiseBannerBean;

    private ActivityBannerBean mActivityBannerBean;

    public FarmingBannerHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mLinkUrl)) {
                    ActivityNav.getInstance().startWebViewActivity(itemView.getContext(), "", mLinkUrl);
                }
            }
        });
    }

    public void bindData(AdvertiseBannerBean entity) {
        mAdvertiseBannerBean = entity;
        FarmingResJO.ObjEntity.AdvertiseListEntity advertiseEntity = entity.advertiseEntity;
        if (null != advertiseEntity) {
            mBannerIv.setImageURI(advertiseEntity.picUrl);
            mLinkUrl = advertiseEntity.httpUrl;
        }
    }

    public void bindData(ActivityBannerBean entity) {
        mActivityBannerBean = entity;
        FarmingResJO.ObjEntity.ActivityListEntity activityEntity = entity.activityEntity;
        if (null != activityEntity) {
            mBannerIv.setImageURI(activityEntity.picUrl);
            mLinkUrl = activityEntity.httpUrl;
        }
    }

    @OnClick(R.id.close_btn)
    public void onClick() {
        if (null != mAdvertiseBannerBean) {
            EventBus.getDefault().post(new DeleteBannerEvent(mAdvertiseBannerBean));
        } else if (null != mActivityBannerBean) {
            EventBus.getDefault().post(new DeleteBannerEvent(mActivityBannerBean));
        }
    }
}
