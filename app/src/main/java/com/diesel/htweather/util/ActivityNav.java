package com.diesel.htweather.util;

import android.content.Context;
import android.content.Intent;

import com.diesel.htweather.GuideActivity;
import com.diesel.htweather.MainActivity;
import com.diesel.htweather.farming.CityManageActivity;
import com.diesel.htweather.farming.FarmingDetailsActivity;
import com.diesel.htweather.farming.FarmingListActivity;
import com.diesel.htweather.farming.MessageActivity;
import com.diesel.htweather.farming.MessageDetailsActivity;
import com.diesel.htweather.farming.TruthDataSettingActivity;
import com.diesel.htweather.farming.WeatherTrendActivity;
import com.diesel.htweather.user.AboutAppActivity;
import com.diesel.htweather.user.ActualFarmingSettingActivity;
import com.diesel.htweather.user.AddWatchPlantActivity;
import com.diesel.htweather.user.CommonWebViewActivity;
import com.diesel.htweather.user.EditUserInfoActivity;
import com.diesel.htweather.user.FeedbackActivity;
import com.diesel.htweather.user.FindPasswordActivity;
import com.diesel.htweather.user.GatherDataActivity;
import com.diesel.htweather.user.HelpCenterActivity;
import com.diesel.htweather.user.LoginActivity;
import com.diesel.htweather.user.ModifyPasswordActivity;
import com.diesel.htweather.user.PlantAndAreaActivity;
import com.diesel.htweather.user.RealNameAuthActivity;
import com.diesel.htweather.user.RegisterActivity;
import com.diesel.htweather.user.SystemSettingActivity;

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

    public void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void startGuideActivity(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }

    //---------------------------------------------------------------------------------------
    // 农事
    //---------------------------------------------------------------------------------------

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
        Intent intent = new Intent(context, FarmingListActivity.class);
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

    //---------------------------------------------------------------------------------------
    // 用户中心
    //---------------------------------------------------------------------------------------

    public void startEditUserInfoActivity(Context context) {
        Intent intent = new Intent(context, EditUserInfoActivity.class);
        context.startActivity(intent);
    }

    public void startSystemSettingActivity(Context context) {
        Intent intent = new Intent(context, SystemSettingActivity.class);
        context.startActivity(intent);
    }

    public void startHelpCenterActivity(Context context) {
        Intent intent = new Intent(context, HelpCenterActivity.class);
        context.startActivity(intent);
    }

    public void startPlantAndAreaActivity(Context context) {
        Intent intent = new Intent(context, PlantAndAreaActivity.class);
        context.startActivity(intent);
    }

    public void startRealNameAuthActivity(Context context) {
        Intent intent = new Intent(context, RealNameAuthActivity.class);
        context.startActivity(intent);
    }

    public void startGatherDataActivity(Context context) {
        Intent intent = new Intent(context, GatherDataActivity.class);
        context.startActivity(intent);
    }

    public void startActualFarmingSettingActivity(Context context) {
        Intent intent = new Intent(context, ActualFarmingSettingActivity.class);
        context.startActivity(intent);
    }

    public void startAddWatchPlantActivity(Context context) {
        Intent intent = new Intent(context, AddWatchPlantActivity.class);
        context.startActivity(intent);
    }

    public void startAboutAppActivity(Context context) {
        Intent intent = new Intent(context, AboutAppActivity.class);
        context.startActivity(intent);
    }

    public void startFeedbackActivity(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }

    public void startModifyPasswordActivity(Context context) {
        Intent intent = new Intent(context, ModifyPasswordActivity.class);
        context.startActivity(intent);
    }

    public void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void startRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public void startFindPasswordActivity(Context context) {
        Intent intent = new Intent(context, FindPasswordActivity.class);
        context.startActivity(intent);
    }

    public void startWebViewActivity(Context context, String webTitle, String webUrl) {
        Intent intent = new Intent(context, CommonWebViewActivity.class);
        IntentExtras.setWebTitle(intent, webTitle);
        IntentExtras.setWebUrl(intent, webUrl);
        context.startActivity(intent);
    }

}
