/*
 * Copyright (C) 2012 重庆新媒农信科技有限公司
 * 版权所有
 *
 * 功能描述：地区实体类
 *
 *
 * 创建标识：duxl 20130721
 */
package com.diesel.htweather.model;

import java.io.Serializable;

/**
 * 功能描述：地区实体类
 */
public class RegionObject implements Serializable {

    /**
     * 地区id
     */
    public String id;

    /**
     * 地区名称
     */
    public String name;

    /**
     * 地区名称全称
     */
    public String fullName;

    /**
     * 父级地区名称
     */
    public String parentName;

    public String showName;

    public String parentShowName;

    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return name;
    }

    @Override
    public String toString() {
        return "RegionObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", parentName='" + parentName + '\'' +
                ", showName='" + showName + '\'' +
                ", parentShowName='" + parentShowName + '\'' +
                '}';
    }
}
