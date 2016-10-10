package com.diesel.htweather.webapi;

import com.diesel.htweather.constant.Api;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/13
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */
public class PlantWebService extends WebService {

    private static PlantWebService sInstance;

    public static PlantWebService getInstance() {
        if (sInstance == null) {
            sInstance = new PlantWebService();
        }
        return sInstance;
    }

    private PlantWebService() {
        super();
    }

    public void getPlantCategory(int areaId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_PLANT_CATEGORY_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("arId", String.valueOf(areaId))
                .build()
                .execute(callback);
    }

    public void getPlantList(String areaId, String categoryId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_PLANT_CATEGORY_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("arId", areaId)
                .addParams("cropParentId", categoryId)
                .build()
                .execute(callback);
    }

    /**
     * 关注农作物
     */
    public void focusCrops(String areaId, String cropIds, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FOCUS_CROPS_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("uaId", areaId)
                .addParams("cropIds", cropIds)
                .build()
                .execute(callback);
    }

    /**
     * 获取种植作物列表
     */
    public void getPlantsAndArea(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_PLANTS_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 添加种植作物
     */
    public void addPlantAndArea(String plantName, String area, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.ADD_PLANT_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("cropName", plantName)
                .addParams("area", area)
                .build()
                .execute(callback);
    }

    /**
     * @param ucId 种植作物设置ID
     */
    public void deletePlantAndArea(int ucId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.DELETE_PLANT_AND_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("ucId", String.valueOf(ucId))
                .build()
                .execute(callback);
    }

    /**
     * 获取采集信息列表
     * @param keywords 标题（模糊筛选）
     */
    public void getGatherData(String keywords, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_GATHER_DATA_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("faTitle", keywords)
                .build()
                .execute(callback);
    }

}
