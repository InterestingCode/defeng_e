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

    String houseAddr;

    String addr;

    String arId;

    String sowingTime; // 播种时间

    String plantingTime; // 种植时间

    String isChecked; // 是否选中，1是2否


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


    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
    }

    public String getArId() {
        return arId;
    }

    public void setArId(String arId) {
        this.arId = arId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
