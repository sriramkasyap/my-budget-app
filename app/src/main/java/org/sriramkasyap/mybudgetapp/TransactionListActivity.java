package org.sriramkasyap.mybudgetapp;

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
    RelativeLayout LoadingIndicatorProgressBar;
    RecentTransactionListAdapter rtlAdaptor;
    public RecyclerView RecentTransactionLayout;
    TextView ErrorMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        LoadingIndicatorProgressBar = (RelativeLayout) findViewById(R.id.pb_loading_indicator);
        rtlAdaptor = new RecentTransactionListAdapter();
        RecentTransactionLayout = (RecyclerView) findViewById(R.id.rv_recent_transactions);
        RecentTransactionLayout.setAdapter(rtlAdaptor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecentTransactionLayout.setLayoutManager(layoutManager);
        RecentTransactionLayout.setHasFixedSize(true);
        ErrorMessageTextView = (TextView) findViewById(R.id.tv_error_display);
//        RenderDetails();
        RenderDetailsNew();
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
        Log.d("Intent", "Refresh");
        RenderDetailsNew();

    }

    private void RenderDetailsNew() {
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
                    }

                    @Override
                    public void onFailure(Call<ArrayList<TransactionItem>> call, Throwable t) {
                        showErrorMessage();
                    }
                });
    }

    public void RenderDetails() {
        new GetAllTransactionTask(LoadingIndicatorProgressBar, rtlAdaptor, ErrorMessageTextView).execute();
    }

    public void showErrorMessage() {
        Toast.makeText(this, "Connecting to Internet Failed. Please Check your connection.", Toast.LENGTH_LONG).show();
    }

    private void showAlert(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


}
