package org.sriramkasyap.mybudgetapp.NetworkUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by srirammeduri on 10/10/17.
 */

public class ApiResponse {

    @SerializedName("message")
    public String Message;

    @SerializedName("status")
    public Boolean Status;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
