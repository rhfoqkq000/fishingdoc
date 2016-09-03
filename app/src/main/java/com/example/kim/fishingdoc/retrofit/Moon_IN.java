package com.example.kim.fishingdoc.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ryu on 2016-08-29.
 */
public interface Moon_IN {
    //http://45.32.61.201:3000/crawler/moonDate?sido_name=%EB%B6%80%EC%82%B0&year=2016&month=08
    @GET("crawler/moonDate")
    Call<Repo> repo(@Query("sido_name") String sido_name, @Query("year") String year, @Query("month") String month);
    //Observable<Repo> repo(@Query("sido_name") String sido_name, @Query("year") String year, @Query("month") String month);
}
