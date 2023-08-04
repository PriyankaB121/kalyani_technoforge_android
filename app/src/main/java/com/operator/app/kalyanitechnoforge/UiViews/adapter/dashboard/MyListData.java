package com.operator.app.kalyanitechnoforge.UiViews.adapter.dashboard;

public class MyListData {
    private String amount;
    private String status;
    private String date;

    public MyListData(String amount, String status, String date) {
        this.amount = amount;
        this.status = status;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
