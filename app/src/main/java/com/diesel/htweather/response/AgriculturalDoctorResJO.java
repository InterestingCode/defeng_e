package com.diesel.htweather.response;


import com.diesel.htweather.depthservice.model.DoctorBean;

/**
 * 农业小博士
 * <p>
 * Created by zhoujiangsen on 2016/10/19.
 */

public class AgriculturalDoctorResJO extends BaseResJO {

    DoctorBean obj;

    public DoctorBean getObj() {
        return obj;
    }

    public void setObj(DoctorBean obj) {
        this.obj = obj;
    }
}
