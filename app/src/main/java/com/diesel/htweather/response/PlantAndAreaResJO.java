package com.diesel.htweather.response;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/29
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class PlantAndAreaResJO extends BaseResJO {

    /**
     * count : 1
     * data : [{"area":"234","cropId":0,"userId":4,"cropName":"13423","ucId":2}]
     */

    public int count;

    /**
     * area : 234
     * cropId : 0
     * userId : 4
     * cropName : 13423
     * ucId : 2
     */

    public List<PlantAndAreaEntity> data;

    public static class PlantAndAreaEntity {

        public String area;

        public int cropId;

        public int userId;

        public String cropName;

        public int ucId;
    }
}
