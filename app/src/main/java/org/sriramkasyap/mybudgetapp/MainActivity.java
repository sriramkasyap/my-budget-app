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

import java.util.ArrayList;

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
        ApiManager.getApiInterface().addTransaction("Snacks", "PopiTonique", 100.0).enqueue(new Callback<TransactionItem>() {
            @Override
            public void onResponse(Call<TransactionItem> call, Response<TransactionItem> response) {
                Log.d("AddTransaction", response.body().toString());
                if(response.isSuccessful()) {
                    showAddTransactionSuccess();
                } else {
                    showErrorMessage();
                }
                if(mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TransactionItem> call, Throwable t) {
                if(mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
                showErrorMessage();
            }
        });

    }

    private void GoToSettingsActivity() {
        Log.d("Intent", "Settings");
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
                            showErrorMessage();
                        }
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<TransactionItem>> call, Throwable t) {
                        showErrorMessage();
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                });
    }

    public void showErrorMessage() {
        Toast.makeText(this, "Connecting to Internet Failed. Please Check your connection.", Toast.LENGTH_LONG).show();
    }

    public void  showAddTransactionSuccess() {
        Toast.makeText(this, "Transaction Added Successfully", Toast.LENGTH_SHORT).show();
    }
}
