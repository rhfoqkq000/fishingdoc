package com.example.kim.fishingdoc.fish;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-09-02.
 */
public class ServiceGenerator {
    public static final String API_BASE_URL = "http://45.32.61.201:3000/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL);

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
