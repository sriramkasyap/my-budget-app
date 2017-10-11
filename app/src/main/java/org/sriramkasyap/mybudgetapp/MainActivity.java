package org.sriramkasyap.mybudgetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.sriramkasyap.mybudgetapp.NetworkUtils.ApiManager;
import org.sriramkasyap.mybudgetapp.NetworkUtils.ApiResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public RecyclerView RecentTransactionLayout;
    public RecentTransactionListAdapter rtlAdaptor;
    public TextView ErrorMessageTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecentTransactionLayout = (RecyclerView) findViewById(R.id.rv_recent_transactions);
        rtlAdaptor = new RecentTransactionListAdapter();
        RecentTransactionLayout.setAdapter(rtlAdaptor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecentTransactionLayout.setLayoutManager(layoutManager);
        RecentTransactionLayout.setHasFixedSize(true);

        ErrorMessageTextView = (TextView) findViewById(R.id.tv_error_display);

        /* Renders Details */
        RenderDetails();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh : RefreshHomeScreen();
                                        break;
            case R.id.action_settings : GoToSettingsActivity();
                                        break;
            case R.id.action_view_all_transactions : GoToTransactionActivity();
                                        break;
            case R.id.action_add_transaction: GoToAddTransactionActivity();
                                        break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void GoToAddTransactionActivity() {
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        String currentDateTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ApiManager.getApiInterface().addTransaction("Snacks", "PopiTonique", 100.0, currentDateTimeString)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        Log.d("AddTransaction", response.body().toString());
                        if(response.isSuccessful()) {
                            if(response.body().getStatus()) {
                                showToast(response.body().getMessage());
                            } else {

                            }
                        } else {
                            showToast(response.body().getMessage());
                        }
                        if(mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        if(mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        showToast("Connecting to server Failed. Please Try again");
                    }
                });

    }

    private void GoToSettingsActivity() {
        Log.d("Intent", "Settings");
        Intent SettingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(SettingsIntent);

    }

    private void GoToTransactionActivity() {
        Intent alltransactionIntent = new Intent(this, TransactionListActivity.class);
        startActivity(alltransactionIntent);
    }

    private void RefreshHomeScreen() {
        RenderDetails();

    }

    private void RenderDetails() {

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        ApiManager.getApiInterface().getAllTransactions()
                .enqueue(new Callback<ArrayList<TransactionItem>>() {


                    @Override
                    public void onResponse(Call<ArrayList<TransactionItem>> call, Response<ArrayList<TransactionItem>> response) {
                        if(response.isSuccessful()) {
                            ArrayList<TransactionItem> TransactionList = response.body();
                            rtlAdaptor.setTransactionData(TransactionList);
                        } else {
                            showToast("Transactions Loaded Successfully");
                        }
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<TransactionItem>> call, Throwable t) {
                        showToast("Connecting to server failed. Please Try Again.");
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                });
    }

    public void  showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
