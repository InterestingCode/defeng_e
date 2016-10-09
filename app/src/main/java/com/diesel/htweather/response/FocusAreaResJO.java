package com.diesel.htweather.response;

import com.diesel.htweather.user.model.PlantBaseBean;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/8
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class FocusAreaResJO extends BaseResJO {

    /**
     * count : 2
     * data : [{"ctName":"重庆","arId":1,"ctId":1,"arName":"江北区","userId":1,"pvName":"重庆市","uaId":1,"pvId":1,"addTime":{"time":1472664090031,"minutes":21,"seconds":30,"hours":1,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":1}},{"ctName":"重庆","arId":2,"ctId":1,"arName":"渝北区","userId":1,"pvName":"重庆市","uaId":2,"pvId":1,"addTime":{"time":1472664090034,"minutes":21,"seconds":30,"hours":1,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":1}}]
     */

    public int count;

    /**
     * ctName : 重庆
     * arId : 1
     * ctId : 1
     * arName : 江北区
     * userId : 1
     * pvName : 重庆市
     * uaId : 1
     * pvId : 1
     * addTime : {"time":1472664090031,"minutes":21,"seconds":30,"hours":1,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":1}
     */

    public List<FocusAreaEntity> data;

    public static class FocusAreaEntity extends PlantBaseBean {

        public String ctName;

        public int arId;

        public int ctId;

        public String arName;

        public int userId;

        public String pvName;

        public int uaId;

        public int pvId;

        /**
         * time : 1472664090031
         * minutes : 21
         * seconds : 30
         * hours : 1
         * month : 8
         * timezoneOffset : -480
         * year : 116
         * day : 4
         * date : 1
         */

        public AddTimeEntity addTime;

        public static class AddTimeEntity {

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
