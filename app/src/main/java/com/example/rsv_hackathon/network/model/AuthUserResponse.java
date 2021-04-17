package com.example.rsv_hackathon.network.model;

import com.google.gson.annotations.SerializedName;

public class AuthUserResponse {

    private String status;

    @SerializedName("status_code")
    private String statusCode;

    public AuthUserResponse(String status, String statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
