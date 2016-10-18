package com.diesel.htweather.response;


import com.diesel.htweather.depthservice.model.OnlineAdvisoryBean;

/**
 * Created by zhoujiangsen on 2016/10/14.
 */

public class OnlineDetailsResJO extends BaseResJO {

    OnlineAdvisoryBean obj;

    public OnlineAdvisoryBean getObj() {
        return obj;
    }

    public OnlineDetailsResJO setObj(OnlineAdvisoryBean obj) {
        this.obj = obj;
        return this;
    }
}
