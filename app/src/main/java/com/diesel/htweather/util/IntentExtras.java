package com.diesel.htweather.util;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.diesel.htweather.response.FarmingResJO;

import java.util.ArrayList;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/27
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class IntentExtras {

    private static final String FARMING_TYPE = "farming_type";

    public static void setFarmingType(@NonNull Intent intent, int farmingType) {
        intent.putExtra(FARMING_TYPE, farmingType);
    }

    public static int getFarmingType(@NonNull Intent intent) {
        return intent.getIntExtra(FARMING_TYPE, 0);
    }

    private static final String AREA_ID = "area_id";

    public static void setAreaId(@NonNull Intent intent, int areaId) {
        intent.putExtra(AREA_ID, areaId);
    }

    public static int getAreaId(@NonNull Intent intent) {
        return intent.getIntExtra(AREA_ID, 0);
    }

    private static final String WEB_TITLE = "web_title";

    public static void setWebTitle(@NonNull Intent intent, String webTitle) {
        intent.putExtra(WEB_TITLE, webTitle);
    }

    public static String getWebTitle(@NonNull Intent intent) {
        return intent.getStringExtra(WEB_TITLE);
    }

    private static final String WEB_URL = "web_url";

    public static void setWebUrl(@NonNull Intent intent, String webUrl) {
        intent.putExtra(WEB_URL, webUrl);
    }

    public static String getWebUrl(@NonNull Intent intent) {
        return intent.getStringExtra(WEB_URL);
    }

    private static final String WEATHER_TREND_DATA = "weather_trend_data";

    public static void setWeatherTrendData(@NonNull Intent intent,
            ArrayList<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> dayWeatherList) {
        intent.putParcelableArrayListExtra(WEATHER_TREND_DATA, dayWeatherList);
    }

    public static ArrayList<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> getWeatherTrendData(
            @NonNull Intent intent) {
        return intent.getParcelableArrayListExtra(WEATHER_TREND_DATA);
    }

    private static final String FARMING_NEWS_ID = "farming_news_id";

    public static void setFarmingNewsId(@NonNull Intent intent, int newsId) {
        intent.putExtra(FARMING_NEWS_ID, newsId);
    }

    public static int getFarmingNewsId(@NonNull Intent intent) {
        return intent.getIntExtra(FARMING_NEWS_ID, 0);
    }

}
