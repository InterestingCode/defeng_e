package com.diesel.htweather.util;

import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/27
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class IntentExtras {

    private static final String FARMING_TYPE = "farming_type";

    public static void setFarmingType(@NonNull Intent intent, int farmingType) {
        intent.putExtra(FARMING_TYPE, farmingType);
    }

    public static int getFarmingType(@NonNull Intent intent) {
        return intent.getIntExtra(FARMING_TYPE, 0);
    }

    private static final String WEB_TITLE = "web_title";

    public static void setWebTitle(@NonNull Intent intent, String webTitle) {
        intent.putExtra(WEB_TITLE, webTitle);
    }

    public static String getWebTitle(@NonNull Intent intent) {
        return intent.getStringExtra(WEB_TITLE);
    }

    private static final String WEB_URL = "web_url";

    public static void setWebUrl(@NonNull Intent intent, String webUrl) {
        intent.putExtra(WEB_URL, webUrl);
    }

    public static String getWebUrl(@NonNull Intent intent) {
        return intent.getStringExtra(WEB_URL);
    }

}
