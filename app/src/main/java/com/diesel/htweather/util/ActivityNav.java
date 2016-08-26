package com.diesel.htweather.util;

import android.content.Context;
import android.content.Intent;

import com.diesel.htweather.farming.CityManageActivity;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/26
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class ActivityNav {

    private static ActivityNav mInstance;

    public static ActivityNav getInstance() {
        if (null == mInstance) {
            mInstance = new ActivityNav();
        }
        return mInstance;
    }

    public void startCityManageActivity(Context context) {
        Intent intent = new Intent(context, CityManageActivity.class);
        context.startActivity(intent);
    }

}
