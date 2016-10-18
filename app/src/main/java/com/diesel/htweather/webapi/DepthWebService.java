package com.diesel.htweather.webapi;

import com.diesel.htweather.constant.Api;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
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
     * @param contentType 1：所有数据； 2：我的咨询
     * @param callback    回调
     */
    public void getOnlineConsultationMessages(String contentType, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.GET_ONLINE_CONSULTATION_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("contentType", contentType)
                .addParams("rows", "20")
                .build()
                .execute(callback);
    }

    /**
     * 获取网络图片
     *
     * @param imagePath 文件路径
     * @param callback
     */
    public void getNetworkBitmap(String imagePath, BitmapCallback callback) {
        OkHttpUtils
                .get()
                .url(imagePath)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
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

}
