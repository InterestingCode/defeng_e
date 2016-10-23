package com.diesel.htweather.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.diesel.htweather.GuideActivity;
import com.diesel.htweather.MainActivity;
import com.diesel.htweather.farming.ActualFarmingActivity;
import com.diesel.htweather.farming.CityManageActivity;
import com.diesel.htweather.farming.FarmingDetailsActivity;
import com.diesel.htweather.farming.FarmingListActivity;
import com.diesel.htweather.farming.LocationActivity;
import com.diesel.htweather.farming.MessageActivity;
import com.diesel.htweather.farming.MessageDetailsActivity;
import com.diesel.htweather.farming.TruthDataSettingActivity;
import com.diesel.htweather.farming.WeatherTrendActivity;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.user.AboutAppActivity;
import com.diesel.htweather.user.ActualFarmingSettingActivity;
import com.diesel.htweather.user.AddCropsActivity;
import com.diesel.htweather.user.CommonWebActivity;
import com.diesel.htweather.user.EditProblemActivity;
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

import java.util.ArrayList;

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

    public void startFarmingPolicyActivity(Context context, int areaId, int farmingType) {
        Intent intent = new Intent(context, FarmingListActivity.class);
        IntentExtras.setFarmingType(intent, farmingType);
        IntentExtras.setAreaId(intent, areaId);
        context.startActivity(intent);
    }

    public void startFarmingDetailsActivity(Context context, int newsId, int farmingType) {
        Intent intent = new Intent(context, FarmingDetailsActivity.class);
        IntentExtras.setFarmingNewsId(intent, newsId);
        IntentExtras.setFarmingType(intent, farmingType);
        context.startActivity(intent);
    }

    public void startActualFarmingActivity(Context context,
            FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity entity) {
        Intent intent = new Intent(context, ActualFarmingActivity.class);
        IntentExtras.setActualFarmingData(intent, entity);
        context.startActivity(intent);
    }

    public void startWeatherTrendActivity(Context context,
            ArrayList<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> dayWeatherList) {
        Intent intent = new Intent(context, WeatherTrendActivity.class);
        IntentExtras.setWeatherTrendData(intent, dayWeatherList);
        context.startActivity(intent);
    }

    public void startTruthDataSettingActivity(Context context) {
        Intent intent = new Intent(context, TruthDataSettingActivity.class);
        context.startActivity(intent);
    }

    public void startLocationActivity(Context context) {
        Intent intent = new Intent(context, LocationActivity.class);
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

    public void startEditProblemActivity(Context context) {
        Intent intent = new Intent(context, EditProblemActivity.class);
        context.startActivity(intent);
    }

    public void startActualFarmingSettingActivity(Context context) {
        Intent intent = new Intent(context, ActualFarmingSettingActivity.class);
        context.startActivity(intent);
    }

    public void startAddWatchPlantActivity(Context context, int areaId) {
        Intent intent = new Intent(context, AddCropsActivity.class);
        IntentExtras.setAreaId(intent, areaId);
        context.startActivity(intent);
    }

    public void startAddWatchPlantActivity(Activity context, int areaId, int requestCode) {
        Intent intent = new Intent(context, AddCropsActivity.class);
        IntentExtras.setAreaId(intent, areaId);
        context.startActivityForResult(intent, requestCode);
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
        startLoginActivity(context, false);
    }

    public void startLoginActivity(Context context, boolean clearTop) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (clearTop) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
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
        Intent intent = new Intent(context, CommonWebActivity.class);
        IntentExtras.setWebTitle(intent, webTitle);
        IntentExtras.setWebUrl(intent, webUrl);
        context.startActivity(intent);
    }

}
