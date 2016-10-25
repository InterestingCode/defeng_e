package com.diesel.htweather.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/25
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class WeatherDataResJO extends BaseResJO {

    /**
     * 用户关注的区域相关信息,包括：天气信息.精准农技。实况数据
     */
    public List<FarmingResJO.ObjEntity.WeatherCropCollEntity> weatherCropColl;

    public static class WeatherCropCollEntity {

        public int arId;

        public String arName;

        public FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity hoursDataList;

        public ArrayList<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity>
                dayWeatherList;

        public List<FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity>
                timelyCropsNewsList;

        public static class HoursDataListEntity {

            /**
             * 空气湿度
             */
            public List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity> airMoistureList;

            /**
             * 降水实况数据
             */
            public List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity> precipitationList;

            /**
             * 土壤温度
             */
            public List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity> soilTempLlist;

            /**
             * 土壤湿度
             */
            public List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity> soilMoistureList;

            /**
             * 日照
             */
            public List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity> sunshineList;

            /**
             * 气温实况数据
             */
            public List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity> airTempList;

            /**
             * 风力
             */
            public List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity.RealDataEntity> windPowerList;

            public static class RealDataEntity {

                public String hours;

                public String value;
            }

            public static class AirMoistureListEntity {

                public String hours;

                public String value;
            }

            public static class PrecipitationListEntity {

                public String hours;

                public String value;
            }

            public static class SoilTempLlistEntity {

                public String hours;

                public String value;
            }

            public static class SoilMoistureListEntity {

                public String hours;

                public String value;
            }

            public static class SunshineListEntity {

                public String hours;

                public String value;
            }

            public static class AirTempListEntity {

                public String hours;

                public String value;
            }

            public static class WindPowerListEntity {

                public String hours;

                public String value;
            }
        }

        public static class DayWeatherListEntity implements Parcelable {

            public String currDate;

            public String currTemp;

            public String windPowerLevel;

            public String currLunarDate;

            public String tempBucket;

            public String weatherContentUrl;

            public String solarTerms;

            public String windPower;

            public String weatherContent;

            public String week;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.currDate);
                dest.writeString(this.currTemp);
                dest.writeString(this.windPowerLevel);
                dest.writeString(this.currLunarDate);
                dest.writeString(this.tempBucket);
                dest.writeString(this.weatherContentUrl);
                dest.writeString(this.solarTerms);
                dest.writeString(this.windPower);
                dest.writeString(this.weatherContent);
                dest.writeString(this.week);
            }

            public DayWeatherListEntity() {
            }

            protected DayWeatherListEntity(Parcel in) {
                this.currDate = in.readString();
                this.currTemp = in.readString();
                this.windPowerLevel = in.readString();
                this.currLunarDate = in.readString();
                this.tempBucket = in.readString();
                this.weatherContentUrl = in.readString();
                this.solarTerms = in.readString();
                this.windPower = in.readString();
                this.weatherContent = in.readString();
                this.week = in.readString();
            }

            public static final Parcelable.Creator<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> CREATOR
                    = new Parcelable.Creator<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity>() {
                @Override
                public FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity createFromParcel(Parcel source) {
                    return new FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity(source);
                }

                @Override
                public FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity[] newArray(int size) {
                    return new FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity[size];
                }
            };
        }

        public static class TimelyCropsNewsListEntity implements Serializable {

            public String content;

            public String sourceWay;

            public String title;

            public String desc;

            public int newsId;

            public int cropId;

            public String sendTime;

            public String cropName;

            public int counts;
        }
    }

}
