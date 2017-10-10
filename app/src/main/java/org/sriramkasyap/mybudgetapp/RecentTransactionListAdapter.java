/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sriramkasyap.mybudgetapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
/*
* This is the adapter for Recent Transactions section's RecyclerView on the home screen.
*
* */

public class RecentTransactionListAdapter extends RecyclerView.Adapter<RecentTransactionListAdapter.RecentTransactionListViewHolder> {

    /*  List of Transactions    */
    ArrayList<TransactionItem> TransactionData;


    @Override
    public RecentTransactionListAdapter.RecentTransactionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recent_transaction_item, parent, false);
        RecentTransactionListViewHolder viewHolder = new RecentTransactionListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecentTransactionListAdapter.RecentTransactionListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(TransactionData == null) {
            return 0;
        }
        return TransactionData.size();
    }

    public void setTransactionData(ArrayList<TransactionItem> transactionData) {
        TransactionData = transactionData;
        Log.d("TransactionData", transactionData.get(0).toString());
        notifyDataSetChanged();
    }
    public class RecentTransactionListViewHolder extends RecyclerView.ViewHolder {
        TextView TransactionTitleTextView;
        TextView TransactionTimeTextView;
        TextView TransactionValueTextView;

        public RecentTransactionListViewHolder(View itemView) {
            super(itemView);
            TransactionTitleTextView = (TextView) itemView.findViewById(R.id.tv_transaction_desc);
            TransactionTimeTextView = (TextView) itemView.findViewById(R.id.tv_transaction_time);
            TransactionValueTextView = (TextView) itemView.findViewById(R.id.tv_transaction_value);
        }

        public void bind(int transactionIndex) {
            TransactionTitleTextView.setText(TransactionData.get(transactionIndex).getTransactionTitle());
            TransactionTimeTextView.setText(TransactionData.get(transactionIndex).getTransactionTimeCreated());
            TransactionValueTextView.setText("\u20B9 " + TransactionData.get(transactionIndex).getTransactionValue() + "/-");
        }


    }
}
