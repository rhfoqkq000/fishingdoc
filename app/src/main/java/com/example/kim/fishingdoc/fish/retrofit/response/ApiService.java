package com.example.kim.fishingdoc.fish.retrofit.response;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author Pratik Butani on 23/4/16.
 */
public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of Contacts
    */
    @Multipart
    @POST("upload.php")
 //   Call<Result> uploadImage(MultipartBody.Part body);
    Call<Result> uploadImage(@Part("MultipartBody.Part") MultipartBody.Part file);
}
