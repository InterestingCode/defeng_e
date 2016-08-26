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

/**
 * 功能描述：地区实体类
 *
 * @author duxl
 */
@SuppressWarnings("serial")
public class RegionObject extends ResponseData {

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
