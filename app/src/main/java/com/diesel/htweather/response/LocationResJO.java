package com.diesel.htweather.response;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/10/13
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class LocationResJO extends BaseResJO {

    public AreaListEntity obj;

    public static class AreaListEntity {

        /**
         * ctId : 250
         * firstChar : Q
         * locationId : 511123
         * cityCode : 0833
         * isHot : 1
         * lng : 103.95
         * isOpen : 1
         * locationCode : 511123000000
         * arId : 2247
         * arName : 犍为县
         * jianpin : QW
         * zipCode : 614400
         * shortName : 犍为
         * lat : 29.21
         * isRecommend : 0
         * pinyin : Qianwei
         */

        public List<HotAreaListEntity> hotAreaList;

        /**
         * ctId : 1
         * firstChar : X
         * locationId : 110102
         * cityCode : 010
         * isHot : 2
         * lng : 116.36
         * isOpen : 2
         * locationCode : 110101000000
         * arId : 2
         * arName : 西城区
         * jianpin : XC
         * zipCode : 100000
         * shortName : 西城
         * lat : 39.93
         * isRecommend : 0
         * pinyin : Xicheng
         */

        public List<RecommendAreaListEntity> recommendAreaList;

        public static class HotAreaListEntity {

            public int ctId;

            public String firstChar;

            public String locationId;

            public String cityCode;

            public int isHot;

            public String lng;

            public int isOpen;

            public String locationCode;

            public int arId;

            public String arName;

            public String jianpin;

            public String zipCode;

            public String shortName;

            public String lat;

            public int isRecommend;

            public String pinyin;
        }

        public static class RecommendAreaListEntity {

            public int ctId;

            public String firstChar;

            public String locationId;

            public String cityCode;

            public int isHot;

            public String lng;

            public int isOpen;

            public String locationCode;

            public int arId;

            public String arName;

            public String jianpin;

            public String zipCode;

            public String shortName;

            public String lat;

            public int isRecommend;

            public String pinyin;
        }
    }
}
