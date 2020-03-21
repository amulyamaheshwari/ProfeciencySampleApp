package com.amulya.test.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amulya.test.interfaces.Api;
import com.amulya.test.pojo.RootDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<RootDataModel> rootDataModel;

    //we will call this method to get the data
    public LiveData<RootDataModel> getData() {
        //if the list is null
        if (rootDataModel == null) {
            rootDataModel = new MutableLiveData<RootDataModel>();
            //we will load it asynchronously from server in this method
            loadDropBoxData();
        }

        //finally we will return the list
        return rootDataModel;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadDropBoxData() {
        System.out.println("<<<<<<<<<<<calling>>>>..");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<RootDataModel> call = api.getDropBoxData();


        call.enqueue(new Callback<RootDataModel>() {
            @Override
            public void onResponse(Call<RootDataModel> call, Response<RootDataModel> response) {

                //finally we are setting the list to our MutableLiveData
                rootDataModel.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RootDataModel> call, Throwable t) {
                // Server error
            }
        });
    }
}
