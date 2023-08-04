package com.operator.app.kalyanitechnoforge.Model;

import java.util.List;
public class KaizenResponse {
    private String st;
    private String msg;
    private List<KaizenList> operator_kaizens;

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

    public List<KaizenList> getOperator_kaizens() {
        return operator_kaizens;
    }

    public void setOperator_kaizens(List<KaizenList> operator_kaizens) {
        this.operator_kaizens = operator_kaizens;
    }
}

