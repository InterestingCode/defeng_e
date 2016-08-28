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

}
