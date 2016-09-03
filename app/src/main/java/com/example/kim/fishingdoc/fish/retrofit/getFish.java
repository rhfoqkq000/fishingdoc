package com.example.kim.fishingdoc.fish.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ryu on 2016-08-29.
 */
public interface getFish {
    //http://45.32.61.201:3000/fish
    @GET("fish")
    Call<Repo> repo();
}
