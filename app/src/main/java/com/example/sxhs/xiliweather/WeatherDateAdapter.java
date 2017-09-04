package com.example.sxhs.xiliweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sxhs on 2017/9/4.
 */

public class WeatherDateAdapter extends ArrayAdapter<WeatherDate> {
    public WeatherDateAdapter(@NonNull Context context, @NonNull List<WeatherDate> weatherDates) {
        super(context,0, weatherDates);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View impWeathXinXi = convertView;
        if (impWeathXinXi == null) {
            impWeathXinXi = LayoutInflater.from(getContext()).inflate(
                    R.layout.view_weather_week, parent, false);
        }

        WeatherDate wd  = getItem(position);

        TextView cityName = (TextView) impWeathXinXi.findViewById(R.id.city_name);
        cityName.setText(wd.getSpDistrict());

        return impWeathXinXi;
    }
}
