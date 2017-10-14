package org.sriramkasyap.mybudgetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.sriramkasyap.mybudgetapp.NetworkUtils.ApiManager;
import org.sriramkasyap.mybudgetapp.NetworkUtils.ApiResponse;
import org.sriramkasyap.mybudgetapp.NetworkUtils.TransactionItem;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTransactionActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText TransactionNameEditText;
    private EditText TransactionDescEditText;
    private EditText TransactionValueEditText;
    private Button SubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        TransactionNameEditText = (EditText) findViewById(R.id.et_transaction_name);
        TransactionValueEditText = (EditText) findViewById(R.id.et_transaction_value);
        TransactionDescEditText = (EditText) findViewById(R.id.et_transaction_desc);
        SubmitButton = (Button) findViewById(R.id.button_add_transaction);
        SubmitButton.setOnClickListener(this);
    }

    private Boolean submitTransaction(TransactionItem submittedItem) {
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        ApiManager.getApiInterface().addTransaction(submittedItem.getTransactionTitle(), submittedItem.getTransactionDesc(), submittedItem.getTransactionValue(), submittedItem.getTransactionTimeCreated())
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if(response.isSuccessful()) {
                            if(response.body().getStatus()) {
                                showToast(response.body().getMessage());
                                goToMainActivity();

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
        return false;
    }

    private void goToMainActivity() {
        this.finish();
    }

    @Override
    public void onClick(View view) {
        String TransactionName = TransactionNameEditText.getText().toString();
        Float TransactionValue = Float.parseFloat(TransactionValueEditText.getText().toString());
        String TransactionDesc = TransactionDescEditText.getText().toString();
        TransactionItem eneteredItem = new TransactionItem(TransactionName, TransactionDesc, TransactionValue);
        submitTransaction(eneteredItem);

    }

    public void  showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
