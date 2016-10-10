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

    /**
     * count : 2
     * data : [{"content":"wrkwerjwek","locationAddr":"重庆市-重庆-江北区5","imgPaths":"234sdf.jpg;dfs.jpg","userFace":"http://www.baidu.com","counts":0,"creatTime":"2016.10.04 09:49","ups":0,"comments":0,"contentId":3,"userNickName":"隔壁小王","userType":"普通会员"},{"content":"wrkwerjwek","locationAddr":"重庆市-重庆-江北区4","imgPaths":"234sdf.jpg;dfs.jpg","userFace":"http://www.baidu.com","counts":0,"creatTime":"2016.10.04 09:49","ups":0,"comments":0,"contentId":3,"userNickName":"隔壁小王","userType":"普通会员"}]
     */

    public int count;

    /**
     * content : wrkwerjwek
     * locationAddr : 重庆市-重庆-江北区5
     * imgPaths : 234sdf.jpg;dfs.jpg
     * userFace : http://www.baidu.com
     * counts : 0
     * creatTime : 2016.10.04 09:49
     * ups : 0
     * comments : 0
     * contentId : 3
     * userNickName : 隔壁小王
     * userType : 普通会员
     */

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
