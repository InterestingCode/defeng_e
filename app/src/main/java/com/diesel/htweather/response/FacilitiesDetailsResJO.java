package com.diesel.htweather.response;


import com.diesel.htweather.depthservice.model.FacilitiesBean;

/**
 * Created by zhoujiangsen on 2016/10/14.
 */

public class FacilitiesDetailsResJO extends BaseResJO {

    FacilitiesBean obj;

    public FacilitiesBean getObj() {
        return obj;
    }

    public void setObj(FacilitiesBean obj) {
        this.obj = obj;
    }
}
