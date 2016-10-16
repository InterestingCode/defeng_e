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

        public List<HotAreaListEntity> hotAreaList;

        public List<HotAreaListEntity> recommendAreaList;

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
