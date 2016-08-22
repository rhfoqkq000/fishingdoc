package com.example.kim.fishingdoc.fish;

import java.util.ArrayList;

/**
 * Created by kim on 2016-08-16.
 */
public class AndroidVersion {
    private ArrayList<String> recyclerViewTitleText;
    private ArrayList<String> recyclerViewImage;

    public ArrayList<String> getrecyclerViewTitleText() {
        return recyclerViewTitleText;
    }

    public void setAndroidVersionName(ArrayList<String> recyclerVietTitleText) {
        this.recyclerViewTitleText = recyclerVietTitleText;
    }

    public ArrayList<String> getrecyclerViewImage() {
        return recyclerViewImage;
    }

    public void setrecyclerViewImage(ArrayList<String> recyclerViewImage) {
        this.recyclerViewImage = recyclerViewImage;
    }
}
