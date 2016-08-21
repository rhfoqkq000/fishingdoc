package com.example.kim.fishingdoc.weather;

import android.graphics.drawable.Drawable;

/**
 * Created by kim on 2016-08-19.
 */
public class Hour_ListData {

    public Drawable micon;
    public String mtext1;
    public String mtext2;
    public String mtext3;

    public void setIcon1(Drawable icon){
        micon=icon;
    }

    public void setText1(String text1){
        mtext1=text1;
    }

    public void setText2(String text2){
        mtext2=text2;
    }

    public void setText3(String text3){
        mtext3=text3;
    }

    public Drawable getIcon1(){
        return this.micon;
    }
    public String getText1(){
        return this.mtext1;
    }
    public String getText2(){
        return this.mtext2;
    }
    public String getText3(){
        return this.mtext3;
    }
}
