package com.example.kim.fishingdoc.retrofit;

import java.util.ArrayList;

/**
 * Created by Ryu on 2016-08-29.
 */
public class Repo {
    Wid wid;
    Section_gen section_jowi;
    Section_jowi section_gen;
    String jowi_result;

    ArrayList<Tide_body> tide_body;
    String tide_result;

    ArrayList<Moon_body> moon_body;
    String moon_result;

    public Wid getWid() {
        return wid;
    }

    public Section_gen getSection_jowi() {
        return section_jowi;
    }

    public Section_jowi getSection_gen() {
        return section_gen;
    }

    public String getJowi_result() {
        return jowi_result;
    }

    public ArrayList<Tide_body> getTide_body() {
        return tide_body;
    }

    public String getTide_result() {
        return tide_result;
    }

    public ArrayList<Moon_body> getMoon_body() {
        return moon_body;
    }

    public String getMoon_result() {
        return moon_result;
    }

}
