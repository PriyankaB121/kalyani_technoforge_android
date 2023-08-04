package com.operator.app.kalyanitechnoforge.Model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("st")
    private String status;

    @SerializedName("msg")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
