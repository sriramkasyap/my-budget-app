package org.sriramkasyap.mybudgetapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionListActivity extends AppCompatActivity {
    public RecentTransactionListAdapter rtlAdaptor;
    public RecyclerView RecentTransactionLayout;
    public TextView ErrorMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        rtlAdaptor = new RecentTransactionListAdapter();
        RecentTransactionLayout = (RecyclerView) findViewById(R.id.rv_recent_transactions);
        RecentTransactionLayout.setAdapter(rtlAdaptor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecentTransactionLayout.setLayoutManager(layoutManager);
        RecentTransactionLayout.setHasFixedSize(true);
        ErrorMessageTextView = (TextView) findViewById(R.id.tv_error_display);
        RenderDetails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem view = (MenuItem) findViewById(R.id.action_view_all_transactions);

        if(view != null) {
            view.setEnabled(false);
        }
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

        }
        return super.onOptionsItemSelected(item);
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




}
