package com.diesel.htweather.model;

import com.diesel.htweather.response.BaseResJo;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/20
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class UserInfoBean {

    /**
     * 昵称
     */
    public String userNickname = "";

    /**
     * 手机号
     */
    public String userMobile = "";

    /**
     * 用户ID
     */
    public int userId;

    /**
     * 性别（1男2女）
     */
    public int userSex;

    /**
     * 所属区域ID
     */
    public int arId;

    /**
     * 所属职业ID
     */
    public int jobId;

    /**
     * 头像地址
     */
    public String userFace = "";

    /**
     * 出生日期，年月日
     */
    public String birthday = "";

    /**
     * 住址
     */
    public String address = "";

    /**
     * 密码
     */
    public String password = "";

    /**
     * 是否已实名认证（1未认证2认证通过3认证不通过）
     */
    public int isTrue;

    /**
     * 真实姓名
     */
    public String realName = "";

    /**
     * 身份证
     */
    public String cardId = "";

    /**
     * 消息提醒（1开启2关闭）
     */
    public int pushWarning;

    /**
     * 区域地址名称，如：重庆-重庆-万州区
     */
    public String areaAddr = "";

    /**
     * 会员类型（1普通会员2：种植大户3：信息员）
     */
    public int userType;

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userNickname='" + userNickname + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", userId=" + userId +
                ", userSex=" + userSex +
                ", arId=" + arId +
                ", jobId=" + jobId +
                ", userFace='" + userFace + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", isTrue=" + isTrue +
                ", realName='" + realName + '\'' +
                ", cardId='" + cardId + '\'' +
                ", pushWarning=" + pushWarning +
                ", areaAddr='" + areaAddr + '\'' +
                ", userType=" + userType +
                '}';
    }
}
