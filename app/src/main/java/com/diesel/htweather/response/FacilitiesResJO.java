package com.diesel.htweather.response;


import com.diesel.htweather.depthservice.model.FacilitiesListBean;

/**
 * Created by zhoujiangsen on 2016/10/14.
 */

public class FacilitiesResJO extends BaseResJO {

    FacilitiesListBean obj;

    public FacilitiesListBean getObj() {
        return obj;
    }

    public void setObj(FacilitiesListBean obj) {
        this.obj = obj;
    }
}
