package com.example.kim.fishingdoc.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ryu on 2016-09-01.
 */
public interface Wid_IN {
    //http://45.32.61.201:3000/weather/hour?sido_name=%EB%B0%B1%EB%A0%B9%EB%8F%84
    @GET("weather/hour")
    Call<Repo> repo(@Query("sido_name") String sido_name);
}
