package org.sriramkasyap.mybudgetapp;

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


public class MainActivity extends AppCompatActivity {
    public RecyclerView RecentTransactionLayout;
    public RecentTransactionListAdapter rtlAdaptor;
    RelativeLayout LoadingIndicatorProgressBar;
    TextView ErrorMessageTextView;


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
        LoadingIndicatorProgressBar = (RelativeLayout) findViewById(R.id.pb_loading_indicator);

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
