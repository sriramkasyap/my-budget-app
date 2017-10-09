package org.sriramkasyap.mybudgetapp;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Sriram Kasyap on 08-10-2017.
 */

public class GetAllTransactionTask extends AsyncTask<Void, Void, String> {
    String response;
    RelativeLayout LoadingIndicatorProgressBar;
    RecentTransactionListAdapter rtlAdaptor;
    TextView ErrorMessageTextView;

    public GetAllTransactionTask(RelativeLayout loadingIndicatorProgressBar, RecentTransactionListAdapter adapter, TextView ErrorMessageTV) {
        LoadingIndicatorProgressBar = loadingIndicatorProgressBar;
        rtlAdaptor = adapter;
        ErrorMessageTextView = ErrorMessageTV;
    }

    @Override
    protected String doInBackground(Void... params) {
        URL builtURL = NetworkUtils.buildAllTransactionURL();
        try {
            response = NetworkUtils.getResponseFromURL(builtURL);

        } catch (java.io.IOException e) {
            e.printStackTrace();
            response = null;
        }

        return response;
    }

    @Override
    protected void onPreExecute() {
//            super.onPreExecute();
        LoadingIndicatorProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
//            Log.d("Networktask",s);
        if(s != null) {
            ArrayList<TransactionItem> TransactionList = NetworkUtils.ExtractTransactions(s);
            rtlAdaptor.setTransactionData(TransactionList);
            ErrorMessageTextView.setVisibility(View.INVISIBLE);
        } else {

            ErrorMessageTextView.setVisibility(View.VISIBLE);
        }
        LoadingIndicatorProgressBar.setVisibility(View.INVISIBLE);
//            testtextView.setText(s);

    }


}
