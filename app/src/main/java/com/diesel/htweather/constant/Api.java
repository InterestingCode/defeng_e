package com.diesel.htweather.constant;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/4
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class Api {

    public static final String SERVER_URL = "http://183.230.182.143:7016";

    // 登陆
    public static final String LOGIN_URL = SERVER_URL + "/nologin/personal/process.c?do=loginForNormal";

    // 注册
    public static final String REGISTER_URL = SERVER_URL + "/nologin/personal/process.c?do=register";

    // 短信验证码发送
    public static final String GET_AUTH_CODE_URL = SERVER_URL + "/nologin/personal/process.c?do=sendSmsCode";

    // 验证短信码
    public static final String VERIFY_AUTH_CODE_URL = SERVER_URL + "/nologin/personal/process.c?do=verifySmsCode";

    // 验证手机
    public static final String VERIFY_MOBILE_URL = SERVER_URL + "/nologin/personal/process.c?do=cbForValiMobile";

    // 重置密码
    public static final String RESET_PASSWORD_URL = SERVER_URL + "/nologin/personal/process.c?do=cbForResetPwd";

    // 修改密码
    public static final String MODIFY_PASSWORD_URL = SERVER_URL + "/session/user/process.c?do=updateUserPwd";

    // 修改用户个人信息
    public static final String MODIFY_USER_INFO_URL = SERVER_URL + "/session/user/process.c?do=updateUserInfo";

    // 获取个人信息
    public static final String GET_USER_INFO_URL = SERVER_URL + "/session/user/process.c?do=getUserInfo";

    // 实名认证申请
    public static final String REAL_NAME_AUTH_URL = SERVER_URL + "/session/user/process.c?do=addTruthVerify";

    // 查询认证情况
    public static final String GET_REAL_NAME_AUTH_INFO_URL = SERVER_URL + "/session/user/process.c?do=getUserTruthStatus";

    // 消息提醒开关设置
    public static final String UPLOAD_PUSH_MESSAGE_SWITCH_URL = SERVER_URL + "/session/user/process.c?do=setUserPushWaring";

    // 留言反馈
    public static final String FEEDBACK_ADVISE_URL = SERVER_URL + "/session/user/process.c?do=addGuestbook";

    // 版本检测
    public static final String CHECK_VERSION_URL = SERVER_URL + "/nologin/common/process.c?do=getVersion";

    // 帮助中心-常见问题
    public static final String COMMON_PROBLEMS_URL = SERVER_URL + "/nologin/apppage/process.c?do=FAQ";

    // 帮助中心-操作指南
    public static final String OPERATION_GUIDE_URL = SERVER_URL + "/nologin/apppage/process.c?do=useGuide";

    // 获取用户已关注区域
    public static final String GET_FOCUS_AREA_URL = SERVER_URL + "/session/user/process.c?do=findFocusAreaByUserId";

    // 新增关注区域
    public static final String ADD_FOCUS_AREA_URL = SERVER_URL + "/session/user/process.c?do=addFocusAreaForUser";

    // 取消关注区域
    public static final String CANCEL_FOCUS_AREA_URL = SERVER_URL + "/session/user/process.c?do=delFocusAreaForUser";

    // 已开通服务区域
    public static final String GET_OPEN_AREA_URL = SERVER_URL + "/nologin/common/process.c?do=getOpenAreaList";

    // 热门推荐区域
    public static final String GET_HOT_AREA_URL = SERVER_URL + "/nologin/common/process.c?do=getHotAreaList";

    // 通过区域名查询
    public static final String GET_AREA_BY_NAME_URL = SERVER_URL + "/nologin/common/process.c?do=findByAreaName";

    // 通过区域ID查询
    public static final String GET_AREA_BY_ID_URL = SERVER_URL + "/nologin/common/process.c?do=findById";

    // 所有区域信息
    public static final String GET_AREA_URL = SERVER_URL + "/nologin/common/process.c?do=getAllAreaList";

    // 获取农作物分类
    public static final String GET_PLANT_CATEGORY_URL = SERVER_URL + "/nologin/common/process.c?do=getAllCropTypeList";

    // 关注农作物
    public static final String GET_FOCUS_PLANT_URL = SERVER_URL + "/session/user/process.c?do=addFocusCropForUser";

    // 获取种植作物列表
    public static final String GET_PLANTS_URL = SERVER_URL + "/session/user/process.c?do=getUserCropSetList";

    // 添加种植作物
    public static final String ADD_PLANT_URL = SERVER_URL + "/session/user/process.c?do=addUserCropSet";

    public static final String DELETE_PLANT_AND_AREA_URL = SERVER_URL + "/session/user/process.c?do=delUserCropSet";

    // 所有职业信息
    public static final String GET_JOB_LIST_URL = SERVER_URL + "/nologin/common/process.c?do=getAllJobList";

    // 获取系统消息
    public static final String GET_SYSTEM_MESSAGE_LIST_URL = SERVER_URL + "/session/user/process.c?do=getUserSysMsg";

    // 获取提醒消息
    public static final String GET_NOTIFY_MESSAGE_LIST_URL = SERVER_URL + "/session/user/process.c?do=getUserWarnMsg";

    // 首页
    public static final String FARMING_URL = SERVER_URL + "/session/home/process.c?do=enterHomePage";

    // 图片上传
    public static final String UPLOAD_FILE_URL = SERVER_URL + "/nologin/common/process.c?do=uploadMediaFile";

}
