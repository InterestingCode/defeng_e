package com.diesel.htweather.response;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/10/21
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class FarmingInfoDetailsResJO extends BaseResJO {

    public FarmingInfoEntity obj;

    public static class FarmingInfoEntity {

        public String content;

        public String title;

        public String sourceWay;

        public int newsId;

        public String desc;

        public String sendTime;

        public String titleImg;

        public int counts;
    }
}
