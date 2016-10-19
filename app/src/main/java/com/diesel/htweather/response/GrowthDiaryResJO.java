package com.diesel.htweather.response;


import com.diesel.htweather.depthservice.model.GrowthDiaryBean;

import java.util.List;

/**
 * 成长日记
 * <p>
 * Created by zhoujiangsen on 2016/10/19.
 */

public class GrowthDiaryResJO extends BaseResJO {

    String count;

    List<GrowthDiaryBean> data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<GrowthDiaryBean> getData() {
        return data;
    }

    public void setData(List<GrowthDiaryBean> data) {
        this.data = data;
    }
}
