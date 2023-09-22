package com.company;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    final static String API_URL = "https://samples.openweathermap.org/data/2.5/weather?id=%d&appid=b6907d289e10d714a6e88b30761fae22";

    public static void main(String[] args) throws IOException {
        System.setProperty("https.proxyHost", "proxy.isu.ru");
        System.setProperty("https.proxyPort", "3128");
        String api_url = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=491cb8d2de9109f411cdd0279c8394c4&units=metric"; // взять из задания или документации
//        URL w_url = new URL(url);
//        InputStream stream = (InputStream) w_url.getContent();
//        Gson gson = new Gson();
//        Weather weather = gson.fromJson(new InputStreamReader(stream), Weather.class);
//        System.out.println(gson.fromJson(new InputStreamReader(stream), Weather.class));
        String[] cities = {"Moscow", "Madrid", "Aleppo", "Amman", "Basrah", "Baku", "Baghdad", "Apia", "Brunei", "Beijing"};
        List<Weather> weathers = new ArrayList<>();
        for(String str : cities){
            URL w_url = new URL(String.format(api_url, str));
            InputStream stream = (InputStream) w_url.getContent();
            Gson gson = new Gson();
            Weather weather = gson.fromJson(new InputStreamReader(stream), Weather.class);
            weathers.add(weather);
        }
        Collections.sort(weathers, new Comparator<Weather>() {
            @Override
            public int compare(Weather o1, Weather o2) {
                float t = o1.main.temp - o2.main.temp;
                return t < 0 ? 1 : t > 0 ? -1 : 0;
            }
        });
        for(Weather w : weathers){
            System.out.println(w.name + " " + (int)w.main.temp);
        }
    }
}
