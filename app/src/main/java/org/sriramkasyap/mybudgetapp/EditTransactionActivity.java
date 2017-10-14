package org.sriramkasyap.mybudgetapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTransactionActivity extends AppCompatActivity {

    private EditText TransactionNameEditText;
    private EditText TransactionDescEditText;
    private EditText TransactionValueEditText;
    private Button SubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);

        TransactionNameEditText = (EditText) findViewById(R.id.et_transaction_name);
        TransactionValueEditText = (EditText) findViewById(R.id.et_transaction_value);
        TransactionDescEditText = (EditText) findViewById(R.id.et_transaction_desc);
        SubmitButton = (Button) findViewById(R.id.button_add_transaction);
        SubmitButton.setOnClickListener((View.OnClickListener) this);


    }
}
