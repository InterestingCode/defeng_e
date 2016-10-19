package com.diesel.htweather.response;


import com.diesel.htweather.depthservice.model.DescBean;

/**
 * 深度服务简介
 * <p>
 * Created by zhoujiangsen on 2016/10/19.
 */

public class DeepServiceDescResJO extends BaseResJO {

    DescBean obj;

    public DescBean getObj() {
        return obj;
    }

    public void setObj(DescBean obj) {
        this.obj = obj;
    }
}
