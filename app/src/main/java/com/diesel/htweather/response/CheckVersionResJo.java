package com.diesel.htweather.response;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/20
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class CheckVersionResJo extends BaseResJo {

    /**
     * status : 1
     * remark :
     * vId : 4
     * type : 2
     * version : 2
     */

    public VersionEntity obj;

    public static class VersionEntity {

        public int status;

        public String remark;

        public int vId;

        public int type;

        public String version;
    }
}
