package com.example.kim.fishingdoc.XmlParser1;

/**
 * Created by Ryu on 2016-08-28.
 */

import android.os.Parcel;
import android.os.Parcelable;


public class FishDatas implements Parcelable {
    private String hour, day, temp, wfkor, pop, ws, wd, reh;

    public FishDatas(String hour, String day, String temp, String wfkor, String pop, String ws, String wd, String reh) {
        this.hour = hour;
        this.day = day;
        this.temp = temp;
        this.wfkor = wfkor;
        this.pop = pop;
        this.ws = ws;
        this.wd = wd;
        this.reh = reh;
    }

    public FishDatas(Parcel in) {
        readFromParcel(in);
    }

    public FishDatas() {
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWfkor() {
        return wfkor;
    }

    public void setWfkor(String wfkor) {
        this.wfkor = wfkor;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getReh() {
        return reh;
    }

    public void setReh(String reh) {
        this.reh = reh;
    }

    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void writeToParcel(Parcel arg0, int arg1) {
        // TODO Auto-generated method stub
        arg0.writeString(hour);
        arg0.writeString(day);
        arg0.writeString(temp);
        arg0.writeString(wfkor);
        arg0.writeString(pop);
        arg0.writeString(ws);
        arg0.writeString(wd);
        arg0.writeString(reh);
    }

    private void readFromParcel(Parcel in) {
        // TODO Auto-generated method stub
        hour = in.readString();
        day = in.readString();
        temp = in.readString();
        wfkor = in.readString();
        pop = in.readString();
        ws = in.readString();
        wd = in.readString();
        reh = in.readString();
    }

    public static final Creator CREATOR = new Creator() {

        public FishDatas createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new FishDatas(source);
        }

        public FishDatas[] newArray(int size) {
            // TODO Auto-generated method stub
            return new FishDatas[size];
        }

    };
}
