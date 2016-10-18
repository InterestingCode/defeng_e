package com.diesel.htweather.depthservice.model;

import java.util.List;

/**
 * Created by mac14 on 16/9/6.
 */
public class OnlineAdvisoryBean {

    String content; // 信息采集内容

    String locationAddr; // 信息采集位置

    String imgPaths; // 图片地址

    String userFace; // 用户头像

    String counts; // 阅读数

    String creatTime; // 发布时间

    String ups; // 点赞数

    String comments; // 品论数

    String contentId; // 在线咨询ID

    String userNickName; // 用户昵称

    String userType; // 用户类型

    List<CommentsBean> cmList; // 评论列表

    public String getContent() {
        return content;
    }

    public OnlineAdvisoryBean setContent(String content) {
        this.content = content;
        return this;
    }

    public String getLocationAddr() {
        return locationAddr;
    }

    public OnlineAdvisoryBean setLocationAddr(String locationAddr) {
        this.locationAddr = locationAddr;
        return this;
    }

    public String getImgPaths() {
        return imgPaths;
    }

    public OnlineAdvisoryBean setImgPaths(String imgPaths) {
        this.imgPaths = imgPaths;
        return this;
    }

    public String getUserFace() {
        return userFace;
    }

    public OnlineAdvisoryBean setUserFace(String userFace) {
        this.userFace = userFace;
        return this;
    }

    public String getCounts() {
        return counts;
    }

    public OnlineAdvisoryBean setCounts(String counts) {
        this.counts = counts;
        return this;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public OnlineAdvisoryBean setCreatTime(String creatTime) {
        this.creatTime = creatTime;
        return this;
    }

    public String getUps() {
        return ups;
    }

    public OnlineAdvisoryBean setUps(String ups) {
        this.ups = ups;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public OnlineAdvisoryBean setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public String getContentId() {
        return contentId;
    }

    public OnlineAdvisoryBean setContentId(String contentId) {
        this.contentId = contentId;
        return this;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public OnlineAdvisoryBean setUserNickName(String userNickName) {
        this.userNickName = userNickName;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public OnlineAdvisoryBean setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public List<CommentsBean> getCmList() {
        return cmList;
    }

    public OnlineAdvisoryBean setCmList(List<CommentsBean> cmList) {
        this.cmList = cmList;
        return this;
    }
}
