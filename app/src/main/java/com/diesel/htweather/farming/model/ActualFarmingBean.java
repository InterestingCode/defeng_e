package com.diesel.htweather.farming.model;

import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.response.FarmingResJO;

import java.util.List;

/**
 * Comments：精准农技
 *
 * @author Diesel
 *         Time: 2016/8/22
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class ActualFarmingBean extends BaseBean {

    public int areaId;

    public List<FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity> mTimelyCropsNewsListEntities;

//    public int cropId;
//
//    public int newsId;
//
//    public String cropName;
//
//    public String title;
//
//    public String content;
//
//    public String desc;
//
//    public String sendTime;
//
//    public void convertTimelyCropsNewsListEntity(FarmingResJO.VersionEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity entity) {
//        cropId = entity.cropId;
//        newsId = entity.newsId;
//        cropName = entity.cropName;
//        title = entity.title;
//        content = entity.content;
//        desc = entity.desc;
//        sendTime = entity.sendTime;
//    }

}
