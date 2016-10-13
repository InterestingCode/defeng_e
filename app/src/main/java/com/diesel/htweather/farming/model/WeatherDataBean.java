package com.diesel.htweather.farming.model;

import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.response.FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/22
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class WeatherDataBean extends BaseBean {

    // 用户关注区域7天天气情况
    List<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> dayWeatherList;
    // 实况数据
    List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity> hoursDataList;

    public String currTemp;

    public String weatherContent;

    public String week;

    public String windPower;

    public String currDate;

    public String currLunarDate;

    public String tempBucket;

    public String weatherContentUrl;

    public String windPowerLevel;

    public void convertDayWeatherListEntity(DayWeatherListEntity entity) {
        currTemp = entity.currTemp;
        weatherContent = entity.weatherContent;
        week = entity.week;
        windPower = entity.windPower;
        currDate = entity.currDate;
        currLunarDate = entity.currLunarDate;
        tempBucket = entity.tempBucket;
        weatherContentUrl = entity.weatherContentUrl;
        windPowerLevel = entity.windPowerLevel;
    }

}
