package com.example.kim.fishingdoc.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ryu on 2016-08-29.
 */
public interface Tide_IN {
    //http://45.32.61.201:3000/crawler/tide?sido=BAEKRYEONGDO&month=201608
    @GET("crawler/tide")
    Call<Repo> repo(@Query("sido") String sido_name, @Query("month") String month);
    //Observable<Repo> repo(@Query("sido") String sido_name, @Query("month") String month);
}
