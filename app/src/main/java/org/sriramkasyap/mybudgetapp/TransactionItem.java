package org.sriramkasyap.mybudgetapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sriram Kasyap on 08-10-2017.
 */

public class TransactionItem {
    public int TransactionID;
    public String TransactionTitle;
    public String TransactionDescription;
    public String TransactionTimeCreated;
    public String TransactionTimeModified;
    public double TransactionValue;

    public TransactionItem(JSONObject transJSON) {
        try {
            this.TransactionID = transJSON.getInt("transaction_id");
            this.TransactionTitle = transJSON.getString("transaction_title");
            this.TransactionDescription = transJSON.getString("transaction_desc");
            this.TransactionTimeCreated = transJSON.getString("transaction_time_created");
            this.TransactionTimeModified= transJSON.getString("transaction_time_modified");
            this.TransactionValue= transJSON.getDouble("transaction_value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public String getTransactionTitle() {
        return TransactionTitle;
    }

    public String getTransactionDescription() {
        return TransactionDescription;
    }

    public String getTransactionTimeCreated() {
        return TransactionTimeCreated;
    }

    public String getTransactionTimeModified() {
        return TransactionTimeModified;
    }

    public double getTransactionValue() {
        return TransactionValue;
    }

    public String getTransactionValueString() {
        String TransValue = String.valueOf((int) getTransactionValue());
        return TransValue;
    }
}
