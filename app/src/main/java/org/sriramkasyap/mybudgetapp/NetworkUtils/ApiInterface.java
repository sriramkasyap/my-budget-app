package org.sriramkasyap.mybudgetapp.NetworkUtils;

import org.sriramkasyap.mybudgetapp.TransactionItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by srirammeduri on 09/10/17.
 */

public interface ApiInterface {

    @GET("transactions")
    Call<ArrayList<TransactionItem>> getAllTransactions();
}
