package com.diesel.htweather.depthservice.model;

import java.io.Serializable;

/**
 * 设施农业列表
 * <p>
 * Created by zhoujiangsen on 16/9/6.
 */
public class FacilitiesBean implements Serializable {

    String csId; // 深度服务ID

    String title; // 深度服务标题

    String cropName;

    String cropTypeName;

    String cropPropertyNames;

    String areaNum;

    String cropAddress;

    String sowingTime; // 播种时间

    String plantingTime; // 种植时间


    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropTypeName() {
        return cropTypeName;
    }

    public void setCropTypeName(String cropTypeName) {
        this.cropTypeName = cropTypeName;
    }

    public String getCropPropertyNames() {
        return cropPropertyNames;
    }

    public void setCropPropertyNames(String cropPropertyNames) {
        this.cropPropertyNames = cropPropertyNames;
    }

    public String getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(String areaNum) {
        this.areaNum = areaNum;
    }

    public String getCropAddress() {
        return cropAddress;
    }

    public void setCropAddress(String cropAddress) {
        this.cropAddress = cropAddress;
    }

    public String getSowingTime() {
        return sowingTime;
    }

    public void setSowingTime(String sowingTime) {
        this.sowingTime = sowingTime;
    }

    public String getPlantingTime() {
        return plantingTime;
    }

    public void setPlantingTime(String plantingTime) {
        this.plantingTime = plantingTime;
    }
}
