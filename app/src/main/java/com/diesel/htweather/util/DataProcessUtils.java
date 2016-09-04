package com.diesel.htweather.util;

import android.text.TextUtils;

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
public class DataProcessUtils {

    public static String getTemperatureWithouUnit(String temperature) {
        String temp = "N";
        if (!TextUtils.isEmpty(temperature) && temperature.endsWith("℃")) {
            temp = temperature.substring(0, temperature.indexOf("℃"));
        }
        return temp;
    }

}
