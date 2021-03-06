package org.sriramkasyap.mybudgetapp.NetworkUtils;

import org.sriramkasyap.mybudgetapp.TransactionItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by srirammeduri on 09/10/17.
 */

public interface ApiInterface {

    @GET("transactions")
    Call<ArrayList<TransactionItem>> getAllTransactions();

    @FormUrlEncoded
    @POST("add-transaction")
    Call<TransactionItem> addTransaction(@Field("transaction_title") String TransactionTitle, @Field("transaction_desc") String TransactionDesc, @Field("transaction_value") Double TransactionValue, @Field("transaction_time") String TransactionTime);
}
