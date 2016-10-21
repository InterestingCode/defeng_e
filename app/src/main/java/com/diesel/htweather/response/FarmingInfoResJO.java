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

public class FarmingInfoResJO extends BaseResJO {

    public ObjEntity obj;

    public static class ObjEntity {

        /**
         * picUrl : http://ubmcmm.baidustatic.com/media/v1/0f000c3tjov6MIrr0s9nlf.jpg
         * httpUrl : http://www.baidu.com
         * bannerId : 5
         */

        public List<BannerEntity> bannerList;

        /**
         * content :
         * sourceWay : 德丰e农
         * title : 果园采后管理做得好 来年产量质量低不了
         * desc : 俗话说得好&ldquo;今年叶子保得好，明年果子产量高&rdquo;，葡萄采收后、树莓落叶期的管理是
         * newsId : 10239
         * sendTime : 2016-10-19
         * titleImg : http://www.defengenong.com:8090/images/upload/contentmsg_1476840412107.jpg
         * counts : 1
         */

        public List<InfoNewsEntity> articleNewsList;

        public static class BannerEntity {

            public String picUrl;

            public String httpUrl;

            public int bannerId;
        }

        public static class InfoNewsEntity extends BaseBean {

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
