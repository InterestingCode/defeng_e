package com.diesel.htweather.webapi;

import com.diesel.htweather.constant.Api;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created by zhoujiangsen on 2016/10/14.
 */

public class DepthWebService extends WebService {

    private static DepthWebService sInstance;

    public static DepthWebService getInstance() {
        if (sInstance == null) {
            sInstance = new DepthWebService();
        }
        return sInstance;
    }

    private DepthWebService() {
        super();
    }

    /**
     * 获取在线咨询列表
     *
     * @param faTitle     标题模糊筛选
     * @param contentType 1：所有数据； 2：我的咨询
     * @param callback    回调
     */
    public void getOnlineConsultationMessages(String faTitle, String page, String contentType, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.GET_ONLINE_CONSULTATION_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("faTitle", faTitle)
                .addParams("contentType", contentType)
                .addParams("page", page)
                .addParams("rows", "20")
                .build()
                .execute(callback);
    }

    /**
     * 在线咨询发布信息
     *
     * @param imagePath 图片地址；多张图片分号分割
     */
    public void publishProblem(String content, String imagePath, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.RELEASE_ONLINE_CONSULTATION_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("content", content)
                .addParams("faTitleimg", imagePath)
                .build()
                .execute(callback);
    }

    /**
     * 在线咨询详情
     *
     * @param contentId 评论ID
     */
    public void getCommentsDetails(String contentId, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.ONLINE_CONSULTATION_DETAILS_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("contentId", contentId)
                .build()
                .execute(callback);
    }

    /**
     * 点赞
     *
     * @param contentId 信息采集ID
     */
    public void thumbsUpComments(String contentId, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.ONLINE_CONSULTATION_AGREE_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("contentId", contentId)
                .build()
                .execute(callback);
    }

    /**
     * 发表评论
     *
     * @param contentId  信息采集ID
     * @param comment    评论内容
     * @param parentCmId 父级评论ID
     * @param callback   回调
     */
    public void publishComments(String contentId, String comment, String parentCmId, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.COMMENT_ONLINE_CONSULTATION_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("contentId", contentId)
                .addParams("cId", "4")
                .addParams("comment", comment)
                .addParams("parentCmId", parentCmId)
                .build()
                .execute(callback);
    }

    /**
     * 未读数清零
     *
     * @param callback 回调
     */
    public void dismissNoReadNumber(Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.ONLINE_UNREAD_NUMBER_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 获取农业小博士信息
     *
     * @param callback 回调
     */
    public void getAgricultureDoctor(Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.AGRICULTURE_DOCTOR_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 获取农事建议信息
     *
     * @param callback 回调
     */
    public void getAgriculturalSuggest(Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.AGRICULTURE_SUGGEST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 获取成长日记列表
     *
     * @param page     页码
     * @param rows     每页数量
     * @param callback 回调
     */
    public void getGrowthDiaryList(String page, String rows, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.GROWTHDIARY_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("page", page)
                .addParams("rows", rows)
                .build()
                .execute(callback);
    }


    /**
     * 获取成长日详情
     *
     * @param id       成长ID
     * @param callback 回调
     */
    public void getGrowthDiaryDetails(String id, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.GROWTHDIARY_DETAILS_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("id", id)
                .build()
                .execute(callback);
    }


    /**
     * 申请设施农业
     *
     * @param cropName          种植作物名称
     * @param cropTypeName      种植作物品种名称
     * @param cropPropertyNames 种植作物特性，多个特性分号分割
     * @param areaNum           种植面积
     * @param arId              大棚所在区域ID
     * @param sowingTime        播种时间，格式：年月日  如：2016-07-15
     * @param plantingTime      种植时间，格式，年月日 如：2016-06-25
     * @param callback          回调
     */
    public void applyFacilitiesAgriculture(String cropName, String cropTypeName, String cropPropertyNames, String areaNum, String arId, String detailAddress, String sowingTime, String plantingTime, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.APPLY_AGRICULTURE_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("cropName", cropName)
                .addParams("cropTypeName", cropTypeName)
                .addParams("cropPropertyNames", cropPropertyNames)
                .addParams("areaNumStr", areaNum)
                .addParams("arId", arId)
                .addParams("addr", detailAddress)
                .addParams("sowingTime", sowingTime)
                .addParams("plantingTime", plantingTime)
                .build()
                .execute(callback);
    }

    /**
     * 获取设施农业列表
     *
     * @param callback 回调
     */
    public void getFacilitiesAgricultureList(Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.FACILITIES_AGRICULTURE_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 设施农业详情
     *
     * @param csId     深度服务ID
     * @param callback 回调
     */

    public void getFacilitiesAgricultureDetails(String csId, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.FACILITIES_AGRICULTURE_DETAILS_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("csId", csId)
                .build()
                .execute(callback);
    }


    /**
     * 深度服务简介
     *
     * @param callback 回调
     */
    public void getDeepServiceDesc(Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.DEEP_SERVICE_DESC_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }


}
