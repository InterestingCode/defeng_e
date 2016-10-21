package com.diesel.htweather.response;

import com.diesel.htweather.base.BaseBean;

import java.util.List;

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

public class FarmingPolicyResJO extends BaseResJO {

    public ObjEntity obj;

    public static class ObjEntity {

        /**
         * picUrl : http://ubmcmm.baidustatic.com/media/v1/0f000c3tjov6MIrr0s9nlf.jpg
         * httpUrl : http://www.baidu.com
         * bannerId : 4
         */

        public List<BannerEntity> bannerList;

        /**
         * content :
         * sourceWay : 德丰e农
         * title : 最新农业政策
         * desc : 发送到发送到说的发送到是
         * newsId : 1
         * sendTime : 2016.09.04 08:06
         * titleImg : http://
         * counts : 10
         */

        public List<PolicyNewsEntity> polcyNewsList;

        public static class BannerEntity {

            public String picUrl;

            public String httpUrl;

            public int bannerId;
        }

        public static class PolicyNewsEntity extends BaseBean {

            public String content;

            public String sourceWay;

            public String title;

            public String desc;

            public int newsId;

            public String sendTime;

            public String titleImg;

            public int counts;
        }
    }
}
