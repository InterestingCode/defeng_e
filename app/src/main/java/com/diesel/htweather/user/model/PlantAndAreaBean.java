package com.diesel.htweather.user.model;

import com.diesel.htweather.response.PlantAndAreaResJO;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/4
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class PlantAndAreaBean extends PlantBaseBean {

    public String plantName;

    public String plantArea;

    public int cropId;

    public int userId;

    public int ucId;

    public void resJOToBean(PlantAndAreaResJO.PlantAndAreaEntity entity) {
        plantArea = entity.area;
        plantName = entity.cropName;
        cropId = entity.cropId;
        userId = entity.userId;
        ucId = entity.ucId;
    }

}
