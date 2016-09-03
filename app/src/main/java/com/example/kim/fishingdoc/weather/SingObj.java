package com.example.kim.fishingdoc.weather;

import java.util.ArrayList;

/**
 * Created by Ryu on 2016-09-02.
 */
public class SingObj {
    private static SingObj singObj = new SingObj();

    private ArrayList<String> moonDateList;
    private ArrayList<String> moonRiseList;
    private ArrayList<String> moonIngList;
    private ArrayList<String> moonSetList;

    private ArrayList<String> tideDateList;
    private ArrayList<String> tideLunaList;
    private ArrayList<String> tideHeightFirst;
    private ArrayList<String> tideHeightSecond;
    private ArrayList<String> tideHeightThird;
    private ArrayList<String> tideHeightFourth;

    private ArrayList<String> hourList;
    private ArrayList<String> dayList;
    private ArrayList<String> tempList;
    private ArrayList<String> wfkorList;
    private ArrayList<String> popList;

    private String today;

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public static SingObj getSingObj() {
        return singObj;
    }

    public ArrayList<String> getMoonDateList() {
        return moonDateList;
    }

    public void setMoonDateList(ArrayList<String> moonDateList) {
        this.moonDateList = moonDateList;
    }

    public ArrayList<String> getMoonRiseList() {
        return moonRiseList;
    }

    public void setMoonRiseList(ArrayList<String> moonRiseList) {
        this.moonRiseList = moonRiseList;
    }

    public ArrayList<String> getMoonIngList() {
        return moonIngList;
    }

    public void setMoonIngList(ArrayList<String> moonIngList) {
        this.moonIngList = moonIngList;
    }

    public ArrayList<String> getMoonSetList() {
        return moonSetList;
    }

    public void setMoonSetList(ArrayList<String> moonSetList) {
        this.moonSetList = moonSetList;
    }

    public ArrayList<String> getTideDateList() {
        return tideDateList;
    }

    public void setTideDateList(ArrayList<String> tideDateList) {
        this.tideDateList = tideDateList;
    }

    public ArrayList<String> getTideLunaList() {
        return tideLunaList;
    }

    public void setTideLunaList(ArrayList<String> tideLunaList) {
        this.tideLunaList = tideLunaList;
    }

    public ArrayList<String> getTideHeightFirst() {
        return tideHeightFirst;
    }

    public void setTideHeightFirst(ArrayList<String> tideHeightFirst) {
        this.tideHeightFirst = tideHeightFirst;
    }

    public ArrayList<String> getTideHeightSecond() {
        return tideHeightSecond;
    }

    public void setTideHeightSecond(ArrayList<String> tideHeightSecond) {
        this.tideHeightSecond = tideHeightSecond;
    }

    public ArrayList<String> getTideHeightThird() {
        return tideHeightThird;
    }

    public void setTideHeightThird(ArrayList<String> tideHeightThird) {
        this.tideHeightThird = tideHeightThird;
    }

    public ArrayList<String> getTideHeightFourth() {
        return tideHeightFourth;
    }

    public void setTideHeightFourth(ArrayList<String> tideHeightFourth) {
        this.tideHeightFourth = tideHeightFourth;
    }

    public ArrayList<String> getHourList() {
        return hourList;
    }

    public void setHourList(ArrayList<String> hourList) {
        this.hourList = hourList;
    }

    public ArrayList<String> getDayList() {
        return dayList;
    }

    public void setDayList(ArrayList<String> dayList) {
        this.dayList = dayList;
    }

    public ArrayList<String> getTempList() {
        return tempList;
    }

    public void setTempList(ArrayList<String> tempList) {
        this.tempList = tempList;
    }

    public ArrayList<String> getWfkorList() {
        return wfkorList;
    }

    public void setWfkorList(ArrayList<String> wfkorList) {
        this.wfkorList = wfkorList;
    }

    public ArrayList<String> getPopList() {
        return popList;
    }

    public void setPopList(ArrayList<String> popList) {
        this.popList = popList;
    }
}
