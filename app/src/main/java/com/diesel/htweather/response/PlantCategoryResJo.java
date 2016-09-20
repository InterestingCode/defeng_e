package com.diesel.htweather.response;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/20
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class PlantCategoryResJo extends BaseResJo {

    /**
     * data : [{"iconfilename":"ico-agc-01","leveltype":1,"name":"大田作物","id":1,"parentid":0},{"iconfilename":"ico-agc-02","leveltype":1,"name":"蔬菜","id":2,"parentid":0},{"iconfilename":"ico-agc-03","leveltype":1,"name":"果树、瓜果","id":3,"parentid":0},{"iconfilename":"ico-agc-04","leveltype":1,"name":"经济作物","id":4,"parentid":0},{"iconfilename":"ico-agc-05","leveltype":1,"name":"特色作物及科普","id":5,"parentid":0}]
     * count : 5
     */

    public int count;

    /**
     * iconfilename : ico-agc-01
     * leveltype : 1
     * name : 大田作物
     * id : 1
     * parentid : 0
     */

    public List<PlantCategoryEntity> data;

    public static class PlantCategoryEntity {

        public String iconfilename;

        public int leveltype;

        public String name;

        public int id;

        public int parentid;
    }
}
