package com.diesel.htweather.response;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/4
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class AreaResJO extends BaseResJO {

    /**
     * data : [{"firstChar":"","zipCode":"","jianpin":"","lng":"","cityCode":"","faCityList":[{"firstChar":"","zipCode":"","ctId":1,"jianpin":"","lng":"","cityCode":"","ctName":"重庆","pinyin":"","pvId":1,"locationId":"","locationCode":"","shortName":"","faAreaList":[{"firstChar":"","arId":1,"zipCode":"","ctId":1,"jianpin":"","lng":"","cityCode":"","arName":"江北区","isOpen":1,"pinyin":"","locationId":"","locationCode":"","shortName":"","isHot":2,"lat":""},{"firstChar":"","arId":2,"zipCode":"","ctId":1,"jianpin":"","lng":"","cityCode":"","arName":"渝北区","isOpen":2,"pinyin":"","locationId":"","locationCode":"","shortName":"","isHot":1,"lat":""}],"lat":""}],"pinyin":"","pvName":"重庆市","pvId":1,"locationId":"","locationCode":"","shortName":"","lat":""},{"firstChar":"","zipCode":"","jianpin":"","lng":"","cityCode":"","faCityList":[{"firstChar":"","zipCode":"","ctId":2,"jianpin":"","lng":"","cityCode":"","ctName":"四川","pinyin":"","pvId":2,"locationId":"","locationCode":"","shortName":"","faAreaList":[{"firstChar":"","arId":3,"zipCode":"","ctId":2,"jianpin":"","lng":"","cityCode":"","arName":"成都","isOpen":2,"pinyin":"","locationId":"","locationCode":"","shortName":"","isHot":2,"lat":""},{"firstChar":"","arId":4,"zipCode":"","ctId":2,"jianpin":"","lng":"","cityCode":"","arName":"南充","isOpen":2,"pinyin":"","locationId":"","locationCode":"","shortName":"","isHot":2,"lat":""}],"lat":""}],"pinyin":"","pvName":"四川省","pvId":2,"locationId":"","locationCode":"","shortName":"","lat":""}]
     * count : 2
     */

    public int count;

    /**
     * firstChar :
     * zipCode :
     * jianpin :
     * lng :
     * cityCode :
     * faCityList : [{"firstChar":"","zipCode":"","ctId":1,"jianpin":"","lng":"","cityCode":"","ctName":"重庆","pinyin":"","pvId":1,"locationId":"","locationCode":"","shortName":"","faAreaList":[{"firstChar":"","arId":1,"zipCode":"","ctId":1,"jianpin":"","lng":"","cityCode":"","arName":"江北区","isOpen":1,"pinyin":"","locationId":"","locationCode":"","shortName":"","isHot":2,"lat":""},{"firstChar":"","arId":2,"zipCode":"","ctId":1,"jianpin":"","lng":"","cityCode":"","arName":"渝北区","isOpen":2,"pinyin":"","locationId":"","locationCode":"","shortName":"","isHot":1,"lat":""}],"lat":""}]
     * pinyin :
     * pvName : 重庆市
     * pvId : 1
     * locationId :
     * locationCode :
     * shortName :
     * lat :
     */

    public List<ProvinceEntity> data;

    public static class ProvinceEntity {

        //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
        public String getPickerViewText() {
            //这里还可以判断文字超长截断再提供显示
            return pvName;
        }

        public String firstChar;

        public String zipCode;

        public String jianpin;

        public String lng;

        public String cityCode;

        public String pinyin;

        public String pvName;

        public int pvId;

        public String locationId;

        public String locationCode;

        public String shortName;

        public String lat;

        /**
         * firstChar :
         * zipCode :
         * ctId : 1
         * jianpin :
         * lng :
         * cityCode :
         * ctName : 重庆
         * pinyin :
         * pvId : 1
         * locationId :
         * locationCode :
         * shortName :
         * faAreaList : [{"firstChar":"","arId":1,"zipCode":"","ctId":1,"jianpin":"","lng":"","cityCode":"","arName":"江北区","isOpen":1,"pinyin":"","locationId":"","locationCode":"","shortName":"","isHot":2,"lat":""},{"firstChar":"","arId":2,"zipCode":"","ctId":1,"jianpin":"","lng":"","cityCode":"","arName":"渝北区","isOpen":2,"pinyin":"","locationId":"","locationCode":"","shortName":"","isHot":1,"lat":""}]
         * lat :
         */

        public List<CityEntity> faCityList;

        public static class CityEntity {

            public String getPickerViewText() {
                return ctName;
            }

            public String firstChar;

            public String zipCode;

            public int ctId;

            public String jianpin;

            public String lng;

            public String cityCode;

            public String ctName;

            public String pinyin;

            public int pvId;

            public String locationId;

            public String locationCode;

            public String shortName;

            public String lat;

            /**
             * firstChar :
             * arId : 1
             * zipCode :
             * ctId : 1
             * jianpin :
             * lng :
             * cityCode :
             * arName : 江北区
             * isOpen : 1
             * pinyin :
             * locationId :
             * locationCode :
             * shortName :
             * isHot : 2
             * lat :
             */

            public List<CountryEntity> faAreaList;

            public static class CountryEntity {

                public String getPickerViewText() {
                    return arName;
                }

                public String firstChar;

                public int arId;

                public String zipCode;

                public int ctId;

                public String jianpin;

                public String lng;

                public String cityCode;

                public String arName;

                public int isOpen;

                public String pinyin;

                public String locationId;

                public String locationCode;

                public String shortName;

                public int isHot;

                public String lat;
            }
        }
    }
}
