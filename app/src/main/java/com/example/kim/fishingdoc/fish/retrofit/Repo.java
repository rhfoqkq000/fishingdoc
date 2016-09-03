package com.example.kim.fishingdoc.fish.retrofit;
import java.util.ArrayList;

/**
 * Created by Ryu on 2016-08-29.
 */
public class Repo {
    ArrayList<doc> doc;
    fish_id fish_id;
    id id;
    img_path img_path;
    name name;

    public ArrayList<doc> getDoc() {
        return doc;
    }
    public fish_id getFish_id() {
        return fish_id;
    }
    public id getId() {
        return id;
    }
    public img_path getImg_path() {
        return img_path;
    }
    public name getName() {
        return name;
    }
}
