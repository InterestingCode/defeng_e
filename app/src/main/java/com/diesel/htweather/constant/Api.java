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

    public static final String LOGIN_URL = SERVER_URL + "/nologin/personal/process.c?do=loginForNormal";

    public static final String GET_AUTH_CODE_URL = SERVER_URL + "/nologin/personal/process.c?do=sendSmsCode";

    public static final String VERIFY_MOBILE_URL = SERVER_URL + "/nologin/personal/process.c?do=cbForValiMobile";

    public static final String RESET_PASSWORD_URL = SERVER_URL + "/nologin/personal/process.c?do=cbForResetPwd";

    public static final String FARMING_URL = SERVER_URL + "/session/home/process.c?do=enterHomePage";

}
