package com.diesel.htweather.farming.model;

import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.response.FarmingResJO;

/**
 * Comments：农气情报
 *
 * @author Diesel
 *         Time: 2016/8/22
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class FarmingInfoBean extends BaseBean {

    public int newsId;

    public int counts;

    public String title;

    public String desc;

    public String sendTime;

    public void convertArticleCropsNewsEntity(FarmingResJO.ObjEntity.ArticleCropsNewsEntity entity) {
        newsId = entity.newsId;
        counts = entity.counts;
        title = entity.title;
        desc = entity.desc;
        sendTime = entity.sendTime;
    }

}
