package com.diesel.htweather.util;

import android.content.Context;
import android.content.Intent;

import com.diesel.htweather.farming.CityManageActivity;
import com.diesel.htweather.farming.FarmingDetailsActivity;
import com.diesel.htweather.farming.FarmingPolicyActivity;
import com.diesel.htweather.farming.MessageActivity;
import com.diesel.htweather.farming.MessageDetailsActivity;
import com.diesel.htweather.farming.TruthDataSettingActivity;
import com.diesel.htweather.farming.WeatherTrendActivity;

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

    public void startMessageActivity(Context context) {
        Intent intent = new Intent(context, MessageActivity.class);
        context.startActivity(intent);
    }

    public void startMessageDetailsActivity(Context context) {
        Intent intent = new Intent(context, MessageDetailsActivity.class);
        context.startActivity(intent);
    }

    public void startFarmingPolicyActivity(Context context, int farmingType) {
        Intent intent = new Intent(context, FarmingPolicyActivity.class);
        IntentExtras.setFarmingType(intent, farmingType);
        context.startActivity(intent);
    }

    public void startFarmingDetailsActivity(Context context) {
        Intent intent = new Intent(context, FarmingDetailsActivity.class);
        context.startActivity(intent);
    }

    public void startWeatherTrendActivity(Context context) {
        Intent intent = new Intent(context, WeatherTrendActivity.class);
        context.startActivity(intent);
    }

    public void startTruthDataSettingActivity(Context context) {
        Intent intent = new Intent(context, TruthDataSettingActivity.class);
        context.startActivity(intent);
    }

}
