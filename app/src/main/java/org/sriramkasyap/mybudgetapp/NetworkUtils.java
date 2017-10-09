package org.sriramkasyap.mybudgetapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sriram Kasyap on 08-10-2017.
 */

public class NetworkUtils {
    public static String BASE_URL = "http://firstshowit.com/skmybudgetapp";
    public static String ALL_TRANSACTIONS_ENDPOINT = "transactions";

    public static URL buildAllTransactionURL() {

        Uri AllTransactionUri = Uri.parse(BASE_URL).buildUpon()
                                    .appendPath(ALL_TRANSACTIONS_ENDPOINT).build();
        URL builtURL = null;
        try {
            builtURL = new URL(AllTransactionUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return builtURL;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if(hasInput) {
                return scanner.next();
            }
            else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<TransactionItem> ExtractTransactions(String JSONData) {
        ArrayList<TransactionItem> TransactionList = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(JSONData);

            int i;
            for(i=0; i<jsonArray.length(); i++) {
                JSONObject transJSON = jsonArray.getJSONObject(i);
                TransactionItem transItem = new TransactionItem(transJSON);
                TransactionList.add(transItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return TransactionList;
    }
}
