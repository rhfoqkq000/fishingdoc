package com.example.kim.fishingdoc.retrofit;

import java.util.ArrayList;

/**
 * Created by Ryu on 2016-08-29.
 */
public class Tide_body {
    String date;
    String luna;
    ArrayList<String> height;

    public String getDate() {
        return date;
    }

    public String getLuna() {
        return luna;
    }

    public ArrayList<String> getHeight() {
        return height;
    }
}
