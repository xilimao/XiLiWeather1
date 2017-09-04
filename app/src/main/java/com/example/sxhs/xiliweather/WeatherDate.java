package com.example.sxhs.xiliweather;

/**
 * Created by sxhs on 2017/9/4.
 */

public class WeatherDate {

    private String spProvince;
    private String spCity;
    private String spDistrict;

    private String tvWeather;
    private String tvTemperature;
    private String tvHumidity;
    private String tvWind;
    private String tvSunrise;
    private String tvSunset;
    private String tvAirCondition;
    private String tvPollution;
    private String tvCold;
    private String tvDressing;
    private String tvExercise;
    private String tvWash;
    private String tvCrawlerTime;

    private String tvWeek;
    private String tvWeekTemperature;
    private String tvWeekDayTime;
    private String tvWeekNight;
    private String tvWeekWind;

//    //地点
//    public WeatherDate(String spDistrict) {
////        this.spProvince = spProvince;
////        this.spCity = spCity;
//        this.spDistrict = spDistrict;
//    }

    //一周的天气
    public WeatherDate(String tvWeek, String tvWeekTemperature, String tvWeekDayTime, String tvWeekNight, String tvWeekWind) {
        this.tvWeek = tvWeek;
        this.tvWeekTemperature = tvWeekTemperature;
        this.tvWeekDayTime = tvWeekDayTime;
        this.tvWeekNight = tvWeekNight;
        this.tvWeekWind = tvWeekWind;
    }
//    //详细信息
//    public WeatherDate(String tvWeather, String tvTemperature, String tvHumidity, String tvWind, String tvSunrise, String tvSunset, String tvAirCondition, String tvPollution, String tvCold, String tvDressing, String tvExercise, String tvWash, String tvCrawlerTime) {
//        this.tvWeather = tvWeather;
//        this.tvTemperature = tvTemperature;
//        this.tvHumidity = tvHumidity;
//        this.tvWind = tvWind;
//        this.tvSunrise = tvSunrise;
//        this.tvSunset = tvSunset;
//        this.tvAirCondition = tvAirCondition;
//        this.tvPollution = tvPollution;
//        this.tvCold = tvCold;
//        this.tvDressing = tvDressing;
//        this.tvExercise = tvExercise;
//        this.tvWash = tvWash;
//        this.tvCrawlerTime = tvCrawlerTime;
//    }

    public String getSpProvince() {
        return spProvince;
    }

    public String getSpCity() {
        return spCity;
    }

    public String getSpDistrict() {
        return spDistrict;
    }

    public String getTvWeather() {
        return tvWeather;
    }

    public String getTvTemperature() {
        return tvTemperature;
    }

    public String getTvHumidity() {
        return tvHumidity;
    }

    public String getTvWind() {
        return tvWind;
    }

    public String getTvSunrise() {
        return tvSunrise;
    }

    public String getTvSunset() {
        return tvSunset;
    }

    public String getTvAirCondition() {
        return tvAirCondition;
    }

    public String getTvPollution() {
        return tvPollution;
    }

    public String getTvCold() {
        return tvCold;
    }

    public String getTvDressing() {
        return tvDressing;
    }

    public String getTvExercise() {
        return tvExercise;
    }

    public String getTvWash() {
        return tvWash;
    }

    public String getTvCrawlerTime() {
        return tvCrawlerTime;
    }

    public String getTvWeek() {
        return tvWeek;
    }

    public String getTvWeekTemperature() {
        return tvWeekTemperature;
    }

    public String getTvWeekDayTime() {
        return tvWeekDayTime;
    }

    public String getTvWeekNight() {
        return tvWeekNight;
    }

    public String getTvWeekWind() {
        return tvWeekWind;
    }
}
