package org.sriramkasyap.mybudgetapp;

import android.app.ProgressDialog;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.sriramkasyap.mybudgetapp.NetworkUtils.ApiManager;
import org.sriramkasyap.mybudgetapp.NetworkUtils.ApiResponse;

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

    private Boolean submitTransaction() {
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        String currentDateTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ApiManager.getApiInterface().addTransaction("Snacks", "PopiTonique", 100.0, currentDateTimeString)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if(response.isSuccessful()) {
                            if(response.body().getStatus()) {
//                                showToast(response.body().getMessage());

                            } else {

                            }
                        } else {
//                            showToast(response.body().getMessage());
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
//                        showToast("Connecting to server Failed. Please Try again");
                    }
                });
        return false;
    }

    @Override
    public void onClick(View view) {
        String TransactionName = TransactionNameEditText.getText().toString();
        Float TransactionValue = Float.parseFloat(TransactionValueEditText.getText().toString());
        String TransactionDesc = TransactionDescEditText.getText().toString();
    }


}
