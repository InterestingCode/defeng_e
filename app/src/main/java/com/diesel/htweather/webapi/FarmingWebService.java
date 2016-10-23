package com.diesel.htweather.webapi;

import com.diesel.htweather.constant.Api;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

public class FarmingWebService extends WebService {

    private static FarmingWebService sInstance;

    public static FarmingWebService getInstance() {
        if (sInstance == null) {
            sInstance = new FarmingWebService();
        }
        return sInstance;
    }

    private FarmingWebService() {
        super();
    }

    public void getFarmingPolicyList(int areaId, int page, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FARMING_POLICY_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("arId", String.valueOf(areaId))
                .addParams("page", String.valueOf(page))
                .addParams("rows", "20")
                .build()
                .execute(callback);
    }

    public void getFarmingPolicyDetail(int contentId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FARMING_POLICY_DETAIL_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("contentId", String.valueOf(contentId))
                .build()
                .execute(callback);
    }

    public void getFarmingInfoList(int areaId, int page, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FARMING_INFO_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("arId", String.valueOf(areaId))
                .addParams("page", String.valueOf(page))
                .addParams("rows", "20")
                .build()
                .execute(callback);
    }

    public void getFarmingInfoDetail(int contentId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FARMING_INFO_DETAIL_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("id", String.valueOf(contentId))
                .build()
                .execute(callback);
    }


    public void getGatherDataDetails(String contentId, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.GATHER_DATA_DETAILS_URL)
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
                .url(Api.PRAISE_GATHER_DATA_URL)
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
                .url(Api.COMMENT_GATHER_DATA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("contentId", contentId)
                .addParams("cId", "3")
                .addParams("comment", comment)
                .addParams("parentCmId", parentCmId)
                .build()
                .execute(callback);
    }

}
