package com.diesel.htweather.map;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.diesel.htweather.base.DFApplication;
import com.diesel.htweather.util.SharedPreferencesUtils;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/8/13
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */
public class BMapLocationListener implements BDLocationListener {

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (null != location && location.getLatitude() != 0 && location.getLongitude() != 0) {
            String address = location.getProvince() + "-" + location.getCity() + "-" + location.getDistrict();
            SharedPreferencesUtils.getInstance(DFApplication.getInstance()).saveLocation(address);
            Log.i("BMapLocationListener", "onReceiveLocation() address="+address);
            DFApplication.getInstance().sendStopLocationBroadcast();
        }
    }
}
