package com.diesel.htweather.event;

import com.diesel.htweather.response.PlantCategoryResJO;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/10/10
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class AddCropsEvent {

    public PlantCategoryResJO.CategoryEntity.CategoryListEntity mEntity;

    public AddCropsEvent(PlantCategoryResJO.CategoryEntity.CategoryListEntity entity) {
        mEntity = entity;
    }
}
