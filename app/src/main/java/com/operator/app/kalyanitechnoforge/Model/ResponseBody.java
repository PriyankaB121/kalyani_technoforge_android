package com.operator.app.kalyanitechnoforge.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseBody {

        @SerializedName("st")
        private int status;

        @SerializedName("msg")
        private String message;

        @SerializedName("operator_details")
        private User user;

        // Getters and setters

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
