package org.sriramkasyap.mybudgetapp.NetworkUtils;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by srirammeduri on 09/10/17.
 */

public class ApiManager {

    public static ApiInterface apiInterface;
    public static void createApiInterface() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://firstshowit.com/skmybudgetapp/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
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
