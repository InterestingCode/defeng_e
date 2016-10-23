package com.diesel.htweather.response;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/10/10
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class GatherDataResJO extends BaseResJO {

    public int count;

    public List<GatherDataEntity> data;

    public static class GatherDataEntity {

        public String content;

        public String locationAddr;

        public String imgPaths;

        public String userFace;

        public int counts;

        public String creatTime;

        public int ups;

        public int comments;

        public int contentId;

        public String userNickName;

        public String userType;
    }
}
