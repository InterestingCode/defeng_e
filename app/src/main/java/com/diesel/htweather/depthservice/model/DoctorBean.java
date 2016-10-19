package com.diesel.htweather.depthservice.model;

import java.io.Serializable;

/**
 * 农业小博士
 * <p>
 * Created by zhoujiangsen on 16/9/6.
 */
public class DoctorBean implements Serializable {

    String content; // 图文详情

    String title; // 标题

    String sourceWay; // 来源

    String newsId; // 文章ID

    String desc; // 简介

    String sendTime; // 发布时间

    String titleImg;

    String counts;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceWay() {
        return sourceWay;
    }

    public void setSourceWay(String sourceWay) {
        this.sourceWay = sourceWay;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }
}
