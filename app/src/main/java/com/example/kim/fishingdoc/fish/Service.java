package com.example.kim.fishingdoc.fish;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by adinugroho
 */

interface Service {
        @Multipart
        @POST("fish/imgUpload")
        Call<ResponseBody> upload(@Query("fish_id")String fish_id,@Query("filename") String filename,@Part MultipartBody.Part file);
}
