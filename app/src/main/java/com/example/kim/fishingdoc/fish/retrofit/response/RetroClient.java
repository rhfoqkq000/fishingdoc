package com.example.kim.fishingdoc.fish.retrofit.response;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/**
 * @author Pratik Butani
 */
public class RetroClient {

    /**
     * Upload URL of your folder with php file name...
     * You will find this file in php_upload folder in this project
     * You can copy that folder and paste in your htdocs folder...
     */

    public RetroClient() {

    }
    private static String id;

    public RetroClient(String id) {
        this.id = id;
    }

    private static final String ROOT_URL = "http://45.32.61.201:3000/"+id+"/imgEdit";

    /**
     * Get Retro Client
     *
     * @return JSON Object
     */
    private static Retrofit getRetroClient() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService() {
        return getRetroClient().create(ApiService.class);
    }
}
