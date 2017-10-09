package org.sriramkasyap.mybudgetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

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
        Log.d("Intent", "Refresh");
        RenderDetails();

    }

    public void RenderDetails() {
        new GetAllTransactionTask(LoadingIndicatorProgressBar, rtlAdaptor, ErrorMessageTextView).execute();
    }

}
