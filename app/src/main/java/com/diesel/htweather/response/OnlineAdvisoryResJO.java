package com.diesel.htweather.response;


import com.diesel.htweather.depthservice.model.OnlineAdvisoryBean;

import java.util.List;

/**
 * Created by zhoujiangsen on 2016/10/14.
 */

public class OnlineAdvisoryResJO extends BaseResJO {


    String count;

    // 在线咨询bean
    List<OnlineAdvisoryBean> data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<OnlineAdvisoryBean> getData() {
        return data;
    }

    public OnlineAdvisoryResJO setData(List<OnlineAdvisoryBean> data) {
        this.data = data;
        return this;
    }
}
