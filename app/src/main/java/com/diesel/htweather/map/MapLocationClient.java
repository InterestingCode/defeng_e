package com.diesel.htweather.map;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.diesel.htweather.base.DFApplication;
import com.diesel.htweather.util.SharedPreferencesUtils;

/**
 * @author Diesel
 *
 *         Time: 2016/8/13
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class MapLocationClient {

    private AMapLocationClient mLocationClient;

    public MapLocationClient(Context context) {
        mLocationClient = new AMapLocationClient(context);

        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(5000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是ture
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(true);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mLocationClient.setLocationOption(mOption);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                // errCode等于0代表定位成功，其他的为定位失败
                if (null != aMapLocation && aMapLocation.getErrorCode() == 0) {
                    LocationAddress address = new LocationAddress();
                    address.province = aMapLocation.getProvince();
                    address.city = aMapLocation.getCity();
                    address.district = aMapLocation.getDistrict();
                    address.address = aMapLocation.getAddress();
                    address.latitude = aMapLocation.getLatitude();
                    address.longitude = aMapLocation.getLongitude();
                    Log.e("BMapLocationListener", "onGetReverseGeoCodeResult() "+address);
                    SharedPreferencesUtils.getInstance(DFApplication.getInstance()).saveLocation(address);
                }
                stopLocation();
            }
        });
    }

    public void startLocation() {
        mLocationClient.startLocation();
    }

    public void stopLocation() {
        mLocationClient.stopLocation();
    }

    public void destroy() {
        stopLocation();
        mLocationClient.onDestroy();
    }

}
