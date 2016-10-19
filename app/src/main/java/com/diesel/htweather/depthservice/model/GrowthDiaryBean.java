package com.diesel.htweather.depthservice.model;

/**
 * Created by zhoujiangsen on 16/10/19.
 */
public class GrowthDiaryBean {

    String id; // 成长日记ID

    String content; // 内容

    String time; // 发布时间

    String title; // 标题

    String desc;

    String readCount; // 阅读数

    String lunarStr; // 农历

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getLunarStr() {
        return lunarStr;
    }

    public void setLunarStr(String lunarStr) {
        this.lunarStr = lunarStr;
    }
}
