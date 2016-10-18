package com.diesel.htweather.depthservice.model;

/**
 * Created by zhoujiangsen on 2016/10/18.
 */

public class CommentsBean {

    String cmId;
    String toUserNickName;
    String comment;
    String fromUserNickName;
    String cmType;   // 1顶级评论（针对信息采集）  2二级评论（针对评论的评论）

    public String getCmId() {
        return cmId;
    }

    public CommentsBean setCmId(String cmId) {
        this.cmId = cmId;
        return this;
    }

    public String getToUserNickName() {
        return toUserNickName;
    }

    public CommentsBean setToUserNickName(String toUserNickName) {
        this.toUserNickName = toUserNickName;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public CommentsBean setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getFromUserNickName() {
        return fromUserNickName;
    }

    public CommentsBean setFromUserNickName(String fromUserNickName) {
        this.fromUserNickName = fromUserNickName;
        return this;
    }

    public String getCmType() {
        return cmType;
    }

    public CommentsBean setCmType(String cmType) {
        this.cmType = cmType;
        return this;
    }
}
