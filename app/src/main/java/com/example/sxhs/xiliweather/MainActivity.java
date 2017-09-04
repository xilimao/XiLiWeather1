package com.example.sxhs.xiliweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button selectCity;
    private TextView cityName;
    String citysName;
    String [] xiangXiea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shengMing();

        selectCity.setOnClickListener(this);
        cityName.setOnClickListener(this);
    }

public void jieShuDate(){
    Intent intent1 = this.getIntent();
    Bundle bundle=new Bundle();
    bundle = intent1.getExtras();
    String [] xiangXiea2 = bundle.getStringArray("xiangXiWeather");
    xiangXiea=xiangXiea2;
    citysName = intent1.getStringExtra("citeName");
    displayDate();
}

public void displayDate(){

    TextView cityName = (TextView) findViewById(R.id.city_name);
    TextView weatherLeiXing  = (TextView) findViewById(R.id.title_wea_leiXing);
    TextView wenDu= (TextView) findViewById(R.id.title_wenDu);


    TextView tvWeather = (TextView) findViewById(R.id.tvWeather);
    TextView tvTemperature = (TextView) findViewById(R.id.tvTemperature);
    TextView tvHumidity = (TextView) findViewById(R.id.tvHumidity);
    TextView tvWind = (TextView) findViewById(R.id.tvWind);
    TextView tvSunrise = (TextView) findViewById(R.id.tvSunrise);
    TextView tvSunset = (TextView) findViewById(R.id.tvSunset);
    TextView tvAirCondition = (TextView) findViewById(R.id.tvAirCondition);
    TextView tvPollution = (TextView) findViewById(R.id.tvPollution);
    TextView tvCold = (TextView) findViewById(R.id.tvCold);
    TextView tvDressing = (TextView) findViewById(R.id.tvDressing);
    TextView tvExercise = (TextView) findViewById(R.id.tvExercise);
    TextView tvWash = (TextView) findViewById(R.id.tvWash);
    TextView tvCrawlerTime = (TextView) findViewById(R.id.tvCrawlerTime);

    cityName.setText(citysName);
    weatherLeiXing.setText(xiangXiea[0]);
    wenDu.setText(xiangXiea[1]);
    tvWeather.setText(xiangXiea[0]);
    tvTemperature.setText(xiangXiea[1]);
    tvHumidity.setText(xiangXiea[2]);
    tvWind.setText(xiangXiea[3]);
    tvSunrise.setText(xiangXiea[4]);
    tvSunset.setText(xiangXiea[5]);
    tvAirCondition.setText(xiangXiea[6]);
    tvPollution.setText(xiangXiea[7]);
    tvCold.setText(xiangXiea[8]);
    tvDressing.setText(xiangXiea[9]);
    tvExercise.setText(xiangXiea[10]);
    tvWash.setText(xiangXiea[11]);
    tvCrawlerTime.setText(xiangXiea[12]);
}

    public void shengMing(){

        selectCity = (Button) findViewById(R.id.select_city);
        cityName = (TextView) findViewById(R.id.city_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_city:
                Intent intent =new Intent(MainActivity.this,SelectCityActivity.class);
                startActivity(intent);
                break;
            case R.id.city_name:
                jieShuDate();
                break;
            default:
                break;
        }
    }
}
