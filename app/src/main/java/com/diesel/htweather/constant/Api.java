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

    public static final String SERVER_URL = "http://183.230.37.112:7016";

    // 登陆
    public static final String LOGIN_URL = SERVER_URL + "/nologin/personal/process.c?do=loginForNormal";

    // 短信验证码发送
    public static final String GET_AUTH_CODE_URL = SERVER_URL + "/nologin/personal/process.c?do=sendSmsCode";

    // 手机验证
    public static final String VERIFY_MOBILE_URL = SERVER_URL + "/nologin/personal/process.c?do=cbForValiMobile";

    // 重置密码
    public static final String RESET_PASSWORD_URL = SERVER_URL + "/nologin/personal/process.c?do=cbForResetPwd";

    // 首页
    public static final String FARMING_URL = SERVER_URL + "/session/home/process.c?do=enterHomePage";

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

    // 获取农作物分类
    public static final String GET_PLANT_CATEGORY_URL = SERVER_URL + "/nologin/common/process.c?do=getAllParentCropList";

    // 获取农作物列表
    public static final String GET_PLANT_LIST_URL = SERVER_URL + "/nologin/common/process.c?do=getAllCropList";

    // 关注农作物
    public static final String GET_FOCUS_PLANT_URL = SERVER_URL + "/session/user/process.c?do=addFocusCropForUser";

}
