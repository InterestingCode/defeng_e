package com.diesel.htweather.response;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/22
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class AboutAppResJO extends BaseResJO {

    public AboutAppEntity obj;

    public static class AboutAppEntity {

        public String content;

        public int passSysId;

        public int fcId;

        public int status;

        public Object passTime;

        public int createSysId;

        public String faTitleimg;

        public String faTitle;

        public int cId;

        public Object addTime;
    }
}
