package com.example.sxhs.xiliweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mob.mobapi.API;
import com.mob.mobapi.APICallback;
import com.mob.mobapi.MobAPI;
import com.mob.mobapi.apis.Weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sxhs on 2017/9/2.
 */


public class SelectCityActivity extends Activity implements APICallback, AdapterView.OnItemSelectedListener {

    private titleBar mTitleBar;//声明标题自定义控件
    private Spinner spProvince;
    private Spinner spCity;
    private Spinner spDistrict;
    private ArrayList<HashMap<String, Object>> resultList;

    String citysName = "";
    String[] xiangXiea = new String[13];
    //    ArrayList<WeatherDate> citysName = new ArrayList<WeatherDate>();
//    ArrayList<WeatherDate> xiangXiWea = new ArrayList<WeatherDate>();
    ArrayList<WeatherDate> weeksWea = new ArrayList<WeatherDate>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city_activity);

        spProvince = (Spinner) findViewById(R.id.spinner_shengJi);
        spProvince.setOnItemSelectedListener(this);

        spCity = (Spinner) findViewById(R.id.spinner_shiJi);
        spCity.setOnItemSelectedListener(this);

        spDistrict = (Spinner) findViewById(R.id.spinner_xianJi);
        spDistrict.setOnItemSelectedListener(this);

        titleContent();

        // 获取API实例，请求支持预报的城市列表
        Weather api = (Weather) MobAPI.getAPI(Weather.NAME);
        api.getSupportedCities(this);



    }

    public void intentDate(){
       Intent intent = new Intent();
        intent.setClass(SelectCityActivity.this, MainActivity.class);

        Bundle bundle=new Bundle();
        intent.putExtra("citeName", citysName);
        bundle.putStringArray("xiangXiWeather", xiangXiea);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
        startActivity(intent);
    }

    public void titleContent() {
        mTitleBar = (titleBar) findViewById(R.id.title);
        mTitleBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentDate();
            }
        });
        mTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectCityActivity.this, "点击右键", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSuccess(API api, int action, Map<String, Object> result) {
        switch (action) {
            case Weather.ACTION_CITYS:
                onDistrictListGot(result);
                break;
            case Weather.ACTION_QUERY:
                onWeatherDetailsGot(result);
//                for(int i = 0;i<xiangXiea.length;i++){
//                    Log.e("dsdsdsdsdsds",xiangXiea[i]);
//                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onError(API api, int action, Throwable details) {
        details.printStackTrace();
        Toast.makeText(this, R.string.error_raise, Toast.LENGTH_SHORT).show();
    }


    //spinner的选择触发事件
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.equals(spProvince)) {
            //声明数组列表
            ArrayList<HashMap<String, Object>> cityList
                    = (ArrayList<HashMap<String, Object>>) resultList.get(position).get("city");
            //声明城市数组列表
            ArrayList<String> cities = new ArrayList<String>();
            //添加到城市列表
            for (HashMap<String, Object> c : cityList) {
                cities.add(com.mob.tools.utils.ResHelper.toString(c.get("city")));
            }
            //声明适配器
            ArrayAdapter<String> spCityAdapter = new ArrayAdapter<String>(SelectCityActivity.this, R.layout.view_weather_district, cities);
            spCity.setAdapter(spCityAdapter);

        } else {
            if (parent.equals(spCity)) {
                ArrayList<HashMap<String, Object>> cityList
                        = (ArrayList<HashMap<String, Object>>) resultList.get(spProvince.getSelectedItemPosition()).get("city");
                ArrayList<HashMap<String, Object>> districtList
                        = (ArrayList<HashMap<String, Object>>) cityList.get(position).get("district");
                ArrayList<String> districts = new ArrayList<String>();
                if (districtList == null || districtList.size() == 0) {
                    // 如果城市没有区县,则区县栏显示城市名
                    districts.add((String) parent.getItemAtPosition(position));
                } else {
                    for (HashMap<String, Object> d : districtList) {
                        districts.add(com.mob.tools.utils.ResHelper.toString(d.get("district")));
                        //添加到城市名的数组列表
                    }
                }
                ArrayAdapter<String> spDistrictAdapter = new ArrayAdapter<String>(SelectCityActivity.this, R.layout.view_weather_district, districts);
                spDistrict.setAdapter(spDistrictAdapter);
            } else if (parent.equals(spDistrict)) {
                // 根据选中的地址，查询天气情况
                String district = (String) spDistrict.getSelectedItem();

                citysName = district;
                //Log.e("dsdsdsdsdsds",citysName);

                Weather api = (Weather) MobAPI.getAPI(Weather.NAME);
                api.queryByCityName(district, this);

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "fsdfsd", Toast.LENGTH_SHORT).show();
    }

    // 显示城市数据
    private void onDistrictListGot(Map<String, Object> result) {
        resultList = (ArrayList<HashMap<String, Object>>) result.get("result");
        ArrayList<String> provinces = new ArrayList<String>();
        for (HashMap<String, Object> province : resultList) {
            provinces.add(com.mob.tools.utils.ResHelper.toString(province.get("province")));
        }
        ArrayAdapter<String> spProvinceAdapter = new ArrayAdapter<String>(SelectCityActivity.this, R.layout.view_weather_district, provinces);
        spProvince.setAdapter(spProvinceAdapter);
    }

    // 显示天气数据
    private void onWeatherDetailsGot(Map<String, Object> result) {
//        TextView tvWeather = (TextView) findViewById(R.id.tvWeather);
//        TextView tvTemperature = (TextView) findViewById(R.id.tvTemperature);
//        TextView tvHumidity = (TextView) findViewById(R.id.tvHumidity);
//        TextView tvWind = (TextView) findViewById(R.id.tvWind);
//        TextView tvSunrise = (TextView) findViewById(R.id.tvSunrise);
//        TextView tvSunset = (TextView) findViewById(R.id.tvSunset);
//        TextView tvAirCondition = (TextView) findViewById(R.id.tvAirCondition);
//        TextView tvPollution = (TextView) findViewById(R.id.tvPollution);
//        TextView tvCold = (TextView) findViewById(R.id.tvCold);
//        TextView tvDressing = (TextView) findViewById(R.id.tvDressing);
//        TextView tvExercise = (TextView) findViewById(R.id.tvExercise);
//        TextView tvWash = (TextView) findViewById(R.id.tvWash);
//        TextView tvCrawlerTime = (TextView) findViewById(R.id.tvCrawlerTime);

        @SuppressWarnings("unchecked")
        ArrayList<HashMap<String, Object>> results = (ArrayList<HashMap<String, Object>>) result.get("result");
        HashMap<String, Object> weather = results.get(0);
        String tvWeather = (com.mob.tools.utils.ResHelper.toString(weather.get("weather")));
        String tvTemperature = (com.mob.tools.utils.ResHelper.toString(weather.get("temperature")));
        String tvHumidity = (com.mob.tools.utils.ResHelper.toString(weather.get("humidity")));
        String tvWind = (com.mob.tools.utils.ResHelper.toString(weather.get("wind")));
        String tvSunrise = (com.mob.tools.utils.ResHelper.toString(weather.get("sunrise")));
        String tvSunset = (com.mob.tools.utils.ResHelper.toString(weather.get("sunset")));
        String tvAirCondition = (com.mob.tools.utils.ResHelper.toString(weather.get("airCondition")));
        String tvPollution = (com.mob.tools.utils.ResHelper.toString(weather.get("pollutionIndex")));
        String tvCold = (com.mob.tools.utils.ResHelper.toString(weather.get("coldIndex")));
        String tvDressing = (com.mob.tools.utils.ResHelper.toString(weather.get("dressingIndex")));
        String tvExercise = (com.mob.tools.utils.ResHelper.toString(weather.get("exerciseIndex")));
        String tvWash = (com.mob.tools.utils.ResHelper.toString(weather.get("washIndex")));
        String time = com.mob.tools.utils.ResHelper.toString(weather.get("updateTime"));
        String date = time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8);
        time = time.substring(8, 10) + ":" + time.substring(10, 12) + ":" + time.substring(12);
        String tvCrawlerTime = (date + " " + time);

        xiangXiea[0] = tvWeather;
        xiangXiea[1] = tvTemperature;
        xiangXiea[2] = tvHumidity;
        xiangXiea[3] = tvWind;
        xiangXiea[4] = tvSunrise;
        xiangXiea[5] = tvSunset;
        xiangXiea[6] = tvAirCondition;
        xiangXiea[7] = tvPollution;
        xiangXiea[8] = tvCold;
        xiangXiea[9] = tvDressing;
        xiangXiea[10] = tvExercise;
        xiangXiea[11] = tvWash;
        xiangXiea[12] = tvCrawlerTime;


        @SuppressWarnings("unchecked")
        ArrayList<HashMap<String, Object>> weeks = (ArrayList<HashMap<String, Object>>) weather.get("future");
        if (weeks != null) {
//            LinearLayout llWeekList = (LinearLayout) findViewById(R.id.llWeekList);
//            llWeekList.removeAllViews();
            for (HashMap<String, Object> week : weeks) {
//                View llWeek = View.inflate(this, R.layout.view_weather_week, null);
//                llWeekList.addView(llWeek);

//                TextView tvWeek = (TextView) llWeek.findViewById(R.id.tvWeek);
//                TextView tvWeekTemperature = (TextView) llWeek.findViewById(R.id.tvWeekTemperature);
//                TextView tvWeekDayTime = (TextView) llWeek.findViewById(R.id.tvWeekDayTime);
//                TextView tvWeekNight = (TextView) llWeek.findViewById(R.id.tvWeekNight);
//                TextView tvWeekWind = (TextView) llWeek.findViewById(R.id.tvWeekWind);

                String tvWeek = (com.mob.tools.utils.ResHelper.toString(week.get("week")));
                String tvWeekTemperature = (com.mob.tools.utils.ResHelper.toString(week.get("temperature")));
                String tvWeekDayTime = (com.mob.tools.utils.ResHelper.toString(week.get("dayTime")));
                String tvWeekNight = (com.mob.tools.utils.ResHelper.toString(week.get("night")));
                String tvWeekWind = (com.mob.tools.utils.ResHelper.toString(week.get("wind")));

//                Log.e("hhhhhhhhhhh",tvWeek+"???"+tvWeekTemperature+"???");
                weeksWea.add(new WeatherDate(tvWeek, tvWeekTemperature, tvWeekDayTime, tvWeekNight,
                        tvWeekWind));


            }
        }
    }

}
