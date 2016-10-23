package com.diesel.htweather.event;

import com.diesel.htweather.farming.model.ActivityBannerBean;
import com.diesel.htweather.farming.model.AdvertiseBannerBean;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/22
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class DeleteBannerEvent {

    public AdvertiseBannerBean mAdvertiseBannerBean;

    public ActivityBannerBean mActivityBannerBean;

    public DeleteBannerEvent(ActivityBannerBean activityBannerBean) {
        mActivityBannerBean = activityBannerBean;
    }

    public DeleteBannerEvent(AdvertiseBannerBean advertiseBannerBean) {

        mAdvertiseBannerBean = advertiseBannerBean;
    }
}
