package com.diesel.htweather.response;

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
public class CheckVersionResJO extends BaseResJO {

    /**
     * status : 1
     * remark :
     * versionCode : 4
     * type : 2
     * versionName : 2
     */

    public VersionEntity obj;

    public static class VersionEntity {

        public int status;

        public String remark;

        public int versionCode;

        public int type;

        public String versionName;
    }

}
