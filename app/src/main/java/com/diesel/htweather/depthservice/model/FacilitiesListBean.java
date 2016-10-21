package com.diesel.htweather.depthservice.model;

import java.io.Serializable;
import java.util.List;

/**
 * 设施农业列表
 * <p>
 * Created by zhoujiangsen on 16/9/6.
 */
public class FacilitiesListBean implements Serializable {

    List<FacilitiesBean> recommendSetList;

    List<FacilitiesBean> ownerSetList;

    public List<FacilitiesBean> getRecommendSetList() {
        return recommendSetList;
    }

    public void setRecommendSetList(List<FacilitiesBean> recommendSetList) {
        this.recommendSetList = recommendSetList;
    }

    public List<FacilitiesBean> getOwnerSetList() {
        return ownerSetList;
    }

    public void setOwnerSetList(List<FacilitiesBean> ownerSetList) {
        this.ownerSetList = ownerSetList;
    }
}
