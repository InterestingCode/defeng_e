package com.diesel.htweather.model;

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
     * 手机号
     */
    public String userMobile;

    /**
     * 昵称
     */
    public String userNickname;

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
    public int areaId;

    /**
     * 所属职业ID
     */
    public int jobId;

    /**
     * 头像地址
     */
    public String userFace;

    /**
     * 出生日期，年月日
     */
    public String userBirthday;

    /**
     * 住址
     */
    public String address;

    /**
     * 密码
     */
    public String password;

    /**
     * birthday :
     * arId : 0
     * isTrue : 1
     * realName :
     * cardId :
     * pushWarning : 1
     * areaAddr :
     * userType : 1
     */
    public String birthday;

    public int arId;

    public int isTrue;

    public String realName;

    public String cardId;

    public int pushWarning;

    public String areaAddr;

    public int userType;

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userMobile='" + userMobile + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userId=" + userId +
                ", userSex=" + userSex +
                ", areaId=" + areaId +
                ", jobId=" + jobId +
                ", userFace='" + userFace + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
