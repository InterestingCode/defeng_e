package com.diesel.htweather.db.model;

import com.diesel.htweather.farming.holder.FocusAreaHolder;
import com.diesel.htweather.user.model.PlantBaseBean;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/26
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public final class FocusArea extends PlantBaseBean {

    public String provinceCode = "";
    public String provinceName = "";
    public String cityCode = "";
    public String cityName = "";
    public String countryCode = "";
    public String countryName = "";
    public String data = "";

    @Override
    public String toString() {
        return "FocusArea{" +
                "provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    //    /**
//     * 以下方法仅在{@link FocusAreaHolder}使用
//     */
//
//    public static final int SHOW_STATUS_LIST = 101;
//
//    public static final int SHOW_STATUS_DELETE = 102;
//
//    public int showStatus = SHOW_STATUS_LIST;
//
//    public void changeToList() {
//        showStatus = SHOW_STATUS_LIST;
//    }
//
//    public void changeToDelete() {
//        showStatus = SHOW_STATUS_DELETE;
//    }
//
//    public void changeToListStatus(boolean showList) {
//        showStatus = showList ? SHOW_STATUS_LIST : SHOW_STATUS_DELETE;
//    }

}
