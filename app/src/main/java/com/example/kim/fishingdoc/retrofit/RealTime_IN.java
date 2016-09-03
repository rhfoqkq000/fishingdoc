package com.example.kim.fishingdoc.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ryu on 2016-08-29.
 */
public interface RealTime_IN {
    //http://45.32.61.201:3000/weather?sido_name=%EA%B0%80%EB%8D%95%EB%8F%84
    @GET("weather")
    Call<Repo> repo(@Query("sido_name") String sido_name);
    //Observable<Repo> repo(@Query("sido_name") String sido_name);
}
