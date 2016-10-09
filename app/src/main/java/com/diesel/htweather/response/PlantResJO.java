package com.diesel.htweather.response;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/4
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class PlantResJO extends BaseResJO {

    /**
     * count : 2
     * data : [{"creationtime":{"time":1464059540123,"minutes":12,"seconds":20,"hours":11,"month":4,"timezoneOffset":-480,"year":116,"day":2,"date":24},"id":4193,"cropcode":"12838","cropcategoryid":2,"locationid":511622,"name":"榨菜（茎用芥菜）"},{"creationtime":{"time":1464059540137,"minutes":12,"seconds":20,"hours":11,"month":4,"timezoneOffset":-480,"year":116,"day":2,"date":24},"id":4194,"cropcode":"12839","cropcategoryid":2,"locationid":511622,"name":"冬瓜"}]
     */

    public int count;

    /**
     * creationtime : {"time":1464059540123,"minutes":12,"seconds":20,"hours":11,"month":4,"timezoneOffset":-480,"year":116,"day":2,"date":24}
     * id : 4193
     * cropcode : 12838
     * cropcategoryid : 2
     * locationid : 511622
     * name : 榨菜（茎用芥菜）
     */

    public List<PlantEntity> data;

    public static class PlantEntity {

        /**
         * time : 1464059540123
         * minutes : 12
         * seconds : 20
         * hours : 11
         * month : 4
         * timezoneOffset : -480
         * year : 116
         * day : 2
         * date : 24
         */

        public CreationtimeEntity creationtime;

        public int id;

        public String cropcode;

        public int cropcategoryid;

        public int locationid;

        public String name;

        public static class CreationtimeEntity {

            public long time;

            public int minutes;

            public int seconds;

            public int hours;

            public int month;

            public int timezoneOffset;

            public int year;

            public int day;

            public int date;
        }
    }

}
