package com.diesel.htweather.response;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/4
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class FarmingResJO extends BaseResJO {

    public ObjEntity obj;

    public static class ObjEntity {

        /**
         * 未读消息数
         */
        public int unreadCounts;

        /**
         * 小贴士信息
         */
        public String tips = "";

        /**
         * 用户关注的区域相关信息,包括：天气信息.精准农技。实况数据
         */
        public List<WeatherCropCollEntity> weatherCropColl;

        /**
         * 广告图，位置：首页中间部分
         */
        public List<AdvertiseListEntity> advertiseList;

        /**
         * 农气情报
         */
        public List<ArticleCropsNewsEntity> articleCropsNews;

        /**
         * 农业政策
         */
        public List<PolcyCropsNewsEntity> polcyCropsNews;

        /**
         * 活动图，位置：首页下面部分
         */
        public List<ActivityListEntity> activityList;

        public static class WeatherCropCollEntity {

            public int arId;

            public String arName;

            /**
             * 用户关注区域7天天气情况（当天+未来6天天气预测，日期根据数组下标递增，即：第一条表示当天天气情况）
             */
            public List<DayWeatherListEntity> dayWeatherList;

            /**
             * 精准农技信息，具体展示数量和用户关注的农作物有关
             */
            public List<TimelyCropsNewsListEntity> timelyCropsNewsList;

            public static class DayWeatherListEntity {

                public String currTemp;

                public String weatherContent;

                public String week;

                public String windPower;

                public String currDate;

                public String currLunarDate;

                public String tempBucket;

                public String weatherContentUrl;

                public String windPowerLevel;
            }

            public static class TimelyCropsNewsListEntity {

                public int cropId;

                public int newsId;

                public String cropName;

                public String title;

                public String content;

                public String desc;

                public String sendTime;
            }
        }

        public static class ArticleCropsNewsEntity {

            public int newsId;

            public int counts;

            public String title;

            public String desc;

            public String sendTime;
        }

        public static class AdvertiseListEntity {

            public String picUrl;

            public int bannerId;

            public String httpUrl;
        }

        public static class ActivityListEntity {

            public String picUrl;

            public int bannerId;

            public String httpUrl;
        }

        public static class PolcyCropsNewsEntity {

            public int newsId;

            public int counts;

            public String title;

            public String desc;

            public String sendTime;
        }
    }
}
