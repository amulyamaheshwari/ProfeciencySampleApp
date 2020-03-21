package com.amulya.test.interfaces;

import com.amulya.test.pojo.RootDataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    @GET("facts.json")
    Call<RootDataModel> getDropBoxData();
}
