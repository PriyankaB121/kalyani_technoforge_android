package com.operator.app.kalyanitechnoforge.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KaizenMasterResponse {
    private String st;
    private String msg;
    @SerializedName("operator_kaizens")
    private KaizenMasterList operatorKaizens;

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public KaizenMasterList getOperatorKaizens() {
        return operatorKaizens;
    }

    public void setOperatorKaizens(KaizenMasterList operatorKaizens) {
        this.operatorKaizens = operatorKaizens;
    }
}

