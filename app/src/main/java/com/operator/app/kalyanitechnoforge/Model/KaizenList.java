package com.operator.app.kalyanitechnoforge.Model;

public class KaizenList {

    private int operator_kaizen_id;
    private String operator_id;
    private String doc_no;
    private String rev_no;
    private String rev_date;
    private String tpm_circle_no;
    private String tpm_circle_name;

    public int getOperator_kaizen_id() {
        return operator_kaizen_id;
    }

    public void setOperator_kaizen_id(int operator_kaizen_id) {
        this.operator_kaizen_id = operator_kaizen_id;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getRev_no() {
        return rev_no;
    }

    public void setRev_no(String rev_no) {
        this.rev_no = rev_no;
    }

    public String getRev_date() {
        return rev_date;
    }

    public void setRev_date(String rev_date) {
        this.rev_date = rev_date;
    }

    public String getTpm_circle_no() {
        return tpm_circle_no;
    }

    public void setTpm_circle_no(String tpm_circle_no) {
        this.tpm_circle_no = tpm_circle_no;
    }

    public String getTpm_circle_name() {
        return tpm_circle_name;
    }

    public void setTpm_circle_name(String tpm_circle_name) {
        this.tpm_circle_name = tpm_circle_name;
    }
}
