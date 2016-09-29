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

    /**
     * readCounts : 0
     * weatherCropColl : [{"arId":1,"dayWeatherList":[{"currTemp":"17℃","weatherContent":"小雨","week":"星期三","windPower":"北风","currDate":"2016-04-13","currLunarDate":"农历三月初七","tempBucket":"16/19℃","weatherContentUrl":"http://","windPowerLevel":"1-3级"},{"currTemp":"19℃","weatherContent":"小雨","week":"星期四","windPower":"南风","currDate":"2016-04-14","currLunarDate":"农历三月初八","tempBucket":"16/22℃","weatherContentUrl":"http://","windPowerLevel":"1-3级"},{"currTemp":"14℃","weatherContent":"小雨","week":"星期五","windPower":"南风","currDate":"2016-04-15","currLunarDate":"农历三月初九","tempBucket":"14/23℃","weatherContentUrl":"http://","windPowerLevel":"1-3级"}],"timelyCropsNewsList":[{"cropId":4193,"newsId":1,"cropName":"榨菜（茎用芥菜）","title":"","content":"近期土壤较干旱，可以适当中耕，中耕可以疏松土壤，利于根系发育，同时可去除田间杂草并使土壤更多地接纳雨水。","desc":"","sendTime":"2016-04-13"},{"cropId":4194,"newsId":2,"cropName":"冬瓜","title":"","content":"禾本科作物对精禾草克比较敏感，喷药时切勿喷到邻近的水稻、玉米、大麦、小麦等禾本科作物上，以免产生药害。","desc":"","sendTime":"2016-04-13"}],"arName":"重庆"},{"arId":2,"dayWeatherList":[{"currTemp":"17℃","weatherContent":"小雨","week":"星期三","windPower":"北风","currDate":"2016-04-13","currLunarDate":"农历三月初七","tempBucket":"16/19℃","weatherContentUrl":"http://","windPowerLevel":"1-3级"},{"currTemp":"19℃","weatherContent":"小雨","week":"星期四","windPower":"南风","currDate":"2016-04-14","currLunarDate":"农历三月初八","tempBucket":"16/22℃","weatherContentUrl":"http://","windPowerLevel":"1-3级"},{"currTemp":"14℃","weatherContent":"小雨","week":"星期五","windPower":"南风","currDate":"2016-04-15","currLunarDate":"农历三月初九","tempBucket":"14/23℃","weatherContentUrl":"http://","windPowerLevel":"1-3级"}],"timelyCropsNewsList":[{"cropId":4193,"newsId":1,"cropName":"榨菜（茎用芥菜）","title":"","content":"近期土壤较干旱，可以适当中耕，中耕可以疏松土壤，利于根系发育，同时可去除田间杂草并使土壤更多地接纳雨水。","desc":"","sendTime":"2016-04-13"},{"cropId":4194,"newsId":2,"cropName":"冬瓜","title":"","content":"禾本科作物对精禾草克比较敏感，喷药时切勿喷到邻近的水稻、玉米、大麦、小麦等禾本科作物上，以免产生药害。","desc":"","sendTime":"2016-04-13"}],"arName":"成都"}]
     * articleCropsNews : [{"newsId":1,"counts":150,"title":"柑橘花期的管理是柑橘丰","desc":"柑橘花期的管理是柑橘丰产优质的关键环节，需做好花期管理工作，采取保花保果","sendTime":"2016-04-13"}]
     * advertiseList : [{"picUrl":"http://","bannerId":2,"httpUrl":"http://www.iqiyi.com"}]
     * activityList : [{"picUrl":"http://","bannerId":1,"httpUrl":"http://www.baidu.com"}]
     * polcyCropsNews : [{"newsId":2,"counts":150,"title":"未来三天可能出现较大强度的降水","desc":"未来三天可能出现较大强度的降水，雨后注意排除田间积水，以防造成根部渍害；同时加强田间锈病、白粉病、赤霉病等病虫害的检查。","sendTime":"2016-04-13"}]
     */

    public ObjEntity obj;

    public static class ObjEntity {

        public int readCounts;

        /**
         * arId : 1
         * dayWeatherList : [{"currTemp":"17℃","weatherContent":"小雨","week":"星期三","windPower":"北风","currDate":"2016-04-13","currLunarDate":"农历三月初七","tempBucket":"16/19℃","weatherContentUrl":"http://","windPowerLevel":"1-3级"},{"currTemp":"19℃","weatherContent":"小雨","week":"星期四","windPower":"南风","currDate":"2016-04-14","currLunarDate":"农历三月初八","tempBucket":"16/22℃","weatherContentUrl":"http://","windPowerLevel":"1-3级"},{"currTemp":"14℃","weatherContent":"小雨","week":"星期五","windPower":"南风","currDate":"2016-04-15","currLunarDate":"农历三月初九","tempBucket":"14/23℃","weatherContentUrl":"http://","windPowerLevel":"1-3级"}]
         * timelyCropsNewsList : [{"cropId":4193,"newsId":1,"cropName":"榨菜（茎用芥菜）","title":"","content":"近期土壤较干旱，可以适当中耕，中耕可以疏松土壤，利于根系发育，同时可去除田间杂草并使土壤更多地接纳雨水。","desc":"","sendTime":"2016-04-13"},{"cropId":4194,"newsId":2,"cropName":"冬瓜","title":"","content":"禾本科作物对精禾草克比较敏感，喷药时切勿喷到邻近的水稻、玉米、大麦、小麦等禾本科作物上，以免产生药害。","desc":"","sendTime":"2016-04-13"}]
         * arName : 重庆
         */

        public List<WeatherCropCollEntity> weatherCropColl;

        /**
         * newsId : 1
         * counts : 150
         * title : 柑橘花期的管理是柑橘丰
         * desc : 柑橘花期的管理是柑橘丰产优质的关键环节，需做好花期管理工作，采取保花保果
         * sendTime : 2016-04-13
         */

        public List<ArticleCropsNewsEntity> articleCropsNews;

        /**
         * picUrl : http://
         * bannerId : 2
         * httpUrl : http://www.iqiyi.com
         */

        public List<AdvertiseListEntity> advertiseList;

        /**
         * picUrl : http://
         * bannerId : 1
         * httpUrl : http://www.baidu.com
         */

        public List<ActivityListEntity> activityList;

        /**
         * newsId : 2
         * counts : 150
         * title : 未来三天可能出现较大强度的降水
         * desc : 未来三天可能出现较大强度的降水，雨后注意排除田间积水，以防造成根部渍害；同时加强田间锈病、白粉病、赤霉病等病虫害的检查。
         * sendTime : 2016-04-13
         */

        public List<PolcyCropsNewsEntity> polcyCropsNews;

        public static class WeatherCropCollEntity {

            public int arId;

            public String arName;

            /**
             * currTemp : 17℃
             * weatherContent : 小雨
             * week : 星期三
             * windPower : 北风
             * currDate : 2016-04-13
             * currLunarDate : 农历三月初七
             * tempBucket : 16/19℃
             * weatherContentUrl : http://
             * windPowerLevel : 1-3级
             */

            public List<DayWeatherListEntity> dayWeatherList;

            /**
             * cropId : 4193
             * newsId : 1
             * cropName : 榨菜（茎用芥菜）
             * title :
             * content : 近期土壤较干旱，可以适当中耕，中耕可以疏松土壤，利于根系发育，同时可去除田间杂草并使土壤更多地接纳雨水。
             * desc :
             * sendTime : 2016-04-13
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
