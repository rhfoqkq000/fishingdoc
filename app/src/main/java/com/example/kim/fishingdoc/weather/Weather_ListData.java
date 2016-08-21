package com.example.kim.fishingdoc.weather;

import android.graphics.drawable.Drawable;

/**
 * Created by kim on 16. 7. 19.
 */
public class Weather_ListData {

    public Drawable micon;
    public String mtext;

    public void setIcon(Drawable icon){
        micon=icon;
    }
    public void setText(String text){
        mtext=text;
    }

    public Drawable getIcon(){
        return this.micon;
    }
    public String getText(){
        return this.mtext;
    }
}
