package com.diesel.htweather.response;

import java.util.ArrayList;

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

public class JobResJO extends BaseResJO {

    /**
     * count : 1
     * data : [{"jobId":1,"jobName":"厨师"}]
     */

    public int count;

    /**
     * jobId : 1
     * jobName : 厨师
     */

    public ArrayList<JobEntity> data;

    public static class JobEntity {

        public int jobId;

        public String jobName;

        public String getPickerViewText() {
            return jobName;
        }
    }
}
