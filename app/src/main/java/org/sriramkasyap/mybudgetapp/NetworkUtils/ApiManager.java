package org.sriramkasyap.mybudgetapp.NetworkUtils;

import org.sriramkasyap.mybudgetapp.NetworkUtils.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by srirammeduri on 09/10/17.
 */

public class ApiManager {

    public static ApiInterface apiInterface;
    public static void createApiInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://firstshowit.com/skmybudgetapp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getApiInterface() {
        if(apiInterface == null) {
            createApiInterface();
        }
        return apiInterface;
    }
}
