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
    public static final String COMMON_PROBLEMS_URL = SERVER_URL + "/nologin/apppage/process.c?do=FAQ&drivenType=02&appkey=b66a5c46acf46c10a601bc8cabe4c074";

    // 帮助中心-操作指南
    public static final String OPERATION_GUIDE_URL = SERVER_URL + "/nologin/apppage/process.c?do=useGuide&drivenType=02&appkey=b66a5c46acf46c10a601bc8cabe4c074";

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
    public static final String FOCUS_CROPS_URL = SERVER_URL + "/session/user/process.c?do=addFocusCropForUser";

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

    // 获取采集信息列表
    public static final String GET_GATHER_DATA_LIST_URL = SERVER_URL + "/nologin/content/process.c?do=getCollContentList";

    // 发布采集信息
    public static final String PUBLISH_PROBLEM_URL = SERVER_URL + "/session/content/process.c?do=createCollContent";

    // 首页
    public static final String FARMING_URL = SERVER_URL + "/session/home/process.c?do=enterHomePage";

    // 图片上传
    public static final String UPLOAD_FILE_URL = SERVER_URL + "/nologin/common/process.c?do=uploadMediaFile";

    // 发布在线咨询
    public static final String RELEASE_ONLINE_CONSULTATION_URL = SERVER_URL + "/session/content/process.c?do=createAskContent";

    // 在线咨询评论
    public static final String COMMENT_ONLINE_CONSULTATION_URL = SERVER_URL + "/session/content/process.c?do=addAskContentComment";

    // 获取在线咨询列表
    public static final String GET_ONLINE_CONSULTATION_LIST_URL = SERVER_URL + "/nologin/content/process.c?do=getAskContentList";

    // 在线咨询点赞
    public static final String ONLINE_CONSULTATION_AGREE_URL = SERVER_URL + "/nologin/content/process.c?do=askContentUps";

    // 在线咨询详情
    public static final String ONLINE_CONSULTATION_DETAILS_URL = SERVER_URL + "/nologin/content/process.c?do=lookAskContentInfo";

    // 在线咨询未读数清零
    public static final String ONLINE_UNREAD_NUMBER_URL = SERVER_URL + "/session/user/process.c?do=udpateCommentUnread";

    // 农业小博士
    public static final String AGRICULTURE_DOCTOR_URL = SERVER_URL + "/nologin/content/process.c?do=getAgriculturalEncyInfo";

    // 农事建议
    public static final String AGRICULTURE_SUGGEST_URL = SERVER_URL + "/nologin/content/process.c?do=getAgriculturalSuggestInfo";

    // 成长日记列表
    public static final String GROWTHDIARY_LIST_URL = SERVER_URL + "/nologin/content/process.c?do=getGrowthDiraryList";

    // 成长日记详情
    public static final String GROWTHDIARY_DETAILS_URL = SERVER_URL + "/nologin/content/process.c?do=lookGrowthDiraryInfo";

    // 设施农业申请
    public static final String APPLY_AGRICULTURE_URL = SERVER_URL + "/session/content/process.c?do=reqCropSet";

    // 设施农业详情
    public static final String FACILITIES_AGRICULTURE_DETAILS_URL = SERVER_URL + "/session/content/process.c?do=lookCropSetInfo";

    // 深度服务简介
    public static final String DEEP_SERVICE_DESC_URL = SERVER_URL + "/nologin/common/process.c?do=deepServiceDesc";

}
