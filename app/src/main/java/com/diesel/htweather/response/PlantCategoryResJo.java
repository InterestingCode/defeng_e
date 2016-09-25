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
     * count : 45
     * data : [{"id":3555,"iconfilename":"ico-agc-01","baseCropList":[{"creationtime":{"time":1458553916257,"minutes":51,"seconds":56,"hours":17,"month":2,"timezoneOffset":-480,"year":116,"day":1,"date":21},"id":3555,"cropcode":"10101","cropcategoryid":1,"locationid":510184,"name":"水稻"}],"name":"水稻","parentid":0,"leveltype":1},{"id":25658,"iconfilename":"ico-agc-02","baseCropList":[{"creationtime":{"time":1474515315000,"minutes":35,"seconds":15,"hours":11,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":22},"id":25658,"cropcode":"50110","cropcategoryid":2,"locationid":510184,"name":"黄花3(二因子)"}],"name":"黄花3(二因子)","parentid":0,"leveltype":1},{"id":25659,"iconfilename":"ico-agc-02","baseCropList":[{"creationtime":{"time":1474515315000,"minutes":35,"seconds":15,"hours":11,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":22},"id":25659,"cropcode":"50111","cropcategoryid":2,"locationid":510184,"name":"黄花2(一因子)"}],"name":"黄花2(一因子)","parentid":0,"leveltype":1},{"id":25660,"iconfilename":"ico-agc-02","baseCropList":[{"creationtime":{"time":1474515315000,"minutes":35,"seconds":15,"hours":11,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":22},"id":25660,"cropcode":"50112","cropcategoryid":2,"locationid":510184,"name":"黄花1(无模型)"}],"name":"黄花1(无模型)","parentid":0,"leveltype":1}]
     */

    public int count;

    /**
     * id : 3555
     * iconfilename : ico-agc-01
     * baseCropList : [{"creationtime":{"time":1458553916257,"minutes":51,"seconds":56,"hours":17,"month":2,"timezoneOffset":-480,"year":116,"day":1,"date":21},"id":3555,"cropcode":"10101","cropcategoryid":1,"locationid":510184,"name":"水稻"}]
     * name : 水稻
     * parentid : 0
     * leveltype : 1
     */

    public List<CategoryEntity> data;

    public static class CategoryEntity {

        public int id;

        public String iconfilename;

        public String name;

        public int parentid;

        public int leveltype;

        /**
         * creationtime : {"time":1458553916257,"minutes":51,"seconds":56,"hours":17,"month":2,"timezoneOffset":-480,"year":116,"day":1,"date":21}
         * id : 3555
         * cropcode : 10101
         * cropcategoryid : 1
         * locationid : 510184
         * name : 水稻
         */

        public List<CategoryListEntity> baseCropList;

        public static class CategoryListEntity {

            /**
             * time : 1458553916257
             * minutes : 51
             * seconds : 56
             * hours : 17
             * month : 2
             * timezoneOffset : -480
             * year : 116
             * day : 1
             * date : 21
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
}
