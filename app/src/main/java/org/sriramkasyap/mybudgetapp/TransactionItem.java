package org.sriramkasyap.mybudgetapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sriram Kasyap on 08-10-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionItem {

    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("transaction_title")
    @Expose
    private String transactionTitle;
    @SerializedName("transaction_desc")
    @Expose
    private String transactionDesc;
    @SerializedName("transaction_time_created")
    @Expose
    private String transactionTimeCreated;
    @SerializedName("transaction_time_modified")
    @Expose
    private String transactionTimeModified;
    @SerializedName("transaction_value")
    @Expose
    private String transactionValue;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionTitle() {
        return transactionTitle;
    }

    public void setTransactionTitle(String transactionTitle) {
        this.transactionTitle = transactionTitle;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getTransactionTimeCreated() {
        return transactionTimeCreated;
    }

    public void setTransactionTimeCreated(String transactionTimeCreated) {
        this.transactionTimeCreated = transactionTimeCreated;
    }

    public String getTransactionTimeModified() {
        return transactionTimeModified;
    }

    public void setTransactionTimeModified(String transactionTimeModified) {
        this.transactionTimeModified = transactionTimeModified;
    }

    public String getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(String transactionValue) {
        this.transactionValue = transactionValue;
    }


}

