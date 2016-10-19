package com.diesel.htweather.response;


import com.diesel.htweather.depthservice.model.GrowthDiaryBean;

/**
 * 成长日记详情
 * <p>
 * Created by zhoujiangsen on 2016/10/19.
 */

public class GrowthDiaryDetailsResJO extends BaseResJO {

    GrowthDiaryBean obj;

    public GrowthDiaryBean getObj() {
        return obj;
    }

    public void setObj(GrowthDiaryBean obj) {
        this.obj = obj;
    }
}
