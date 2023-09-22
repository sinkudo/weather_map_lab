package com.company;

import java.util.Comparator;

public class Weather implements Comparator<Weather> {
    Sys sys;
    M main;
    String name;
    @Override
    public int compare(Weather o1, Weather o2) {
        float t = o1.main.temp - o2.main.temp;
        int a = 0;
        if(t > 0)
            a = 1;
        else if (t < 0)
            a = -1;
        return a;
    }
}
