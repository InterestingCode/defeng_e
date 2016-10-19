package com.diesel.htweather.response;


import com.diesel.htweather.depthservice.model.SuggestBean;

/**
 * 农事建议
 * <p>
 * Created by zhoujiangsen on 2016/10/19.
 */

public class AgriculturalSuggestResJO extends BaseResJO {

    SuggestBean obj;

    public SuggestBean getObj() {
        return obj;
    }

    public void setObj(SuggestBean obj) {
        this.obj = obj;
    }
}
