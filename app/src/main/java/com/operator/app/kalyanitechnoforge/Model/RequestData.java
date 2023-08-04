package com.operator.app.kalyanitechnoforge.Model;


import java.util.List;

public class RequestData {
    private int operator_kaizen_id;
    private List<String> activity_pillers;
    private List<Integer> standard_loss_number1;
    private List<Integer> standard_loss_number2;
    private List<Integer> standard_loss_number3;
    private List<Integer> standard_loss_number6;
    private List<Integer> standard_loss_number7;
    private List<Integer> standard_loss_number8;
    private List<Integer> standard_loss_number9;
    private List<String> result_area;
    private String doc_no;
    private String rev_no;
    private String rev_date;
    private String tpm_circle_no;
    private String tpm_circle_name;
    private String kaizen_statrt_date;
    private String kaizen_end_date;
    private String theme;
    private String rev_details;
    private String shop;
    private String date_of_implimentation;
    private String machine;
    private String idea_by;
    private String present_status;
    private String countermeasure;
    private String result;
    private String benefits;

    public RequestData(int operator_kaizen_id, List<String> activityPillers, List<Integer> standardsNo1,
                       List<Integer> standardNo2, List<Integer> standardNo3, List<Integer> standardNo6,
                       List<Integer> standardNo7, List<Integer> standardNo8, List<Integer> standardNo9,
                       List<String> resultArea, String docNo, String revNo, String revDate, String tpmCircleNo,
                       String tpmCircleName, String startDate, String endDate, String theme, String revDetails,
                       String shop, String dateOfImp, String machine, String ideaBy, String presentStatus,
                       String countermeasure, String result, String benefits) {
    this.operator_kaizen_id=operator_kaizen_id;
    this.activity_pillers=activityPillers;
    this.standard_loss_number1=standardsNo1;
    this.standard_loss_number2=standardNo2;
    this.standard_loss_number3=standardNo3;
    this.standard_loss_number6=standardNo6;
    this.standard_loss_number7=standardNo7;
    this.standard_loss_number8=standardNo8;
    this.standard_loss_number9=standardNo9;
    this.result_area=resultArea;
    this.doc_no=docNo;
    this.rev_no=revNo;
    this.rev_date=revDate;
    this.tpm_circle_no=tpmCircleNo;
    this.tpm_circle_name=tpmCircleName;
    this.kaizen_statrt_date=startDate;
    this.kaizen_end_date=endDate;
        this.theme=theme;
    this.rev_details=revDetails;
    this.shop=shop;
    this.date_of_implimentation=dateOfImp;
    this.machine=machine;
    this.idea_by=ideaBy;
    this.present_status=presentStatus;
    this.countermeasure=countermeasure;
    this.result=result;
    this.benefits=benefits;

    }

    public int getOperator_kaizen_id() {
        return operator_kaizen_id;
    }

    public void setOperator_kaizen_id(int operator_kaizen_id) {
        this.operator_kaizen_id = operator_kaizen_id;
    }

    public List<String> getActivity_pillers() {
        return activity_pillers;
    }

    public void setActivity_pillers(List<String> activity_pillers) {
        this.activity_pillers = activity_pillers;
    }

    public List<Integer> getStandard_loss_number1() {
        return standard_loss_number1;
    }

    public void setStandard_loss_number1(List<Integer> standard_loss_number1) {
        this.standard_loss_number1 = standard_loss_number1;
    }

    public List<Integer> getStandard_loss_number2() {
        return standard_loss_number2;
    }

    public void setStandard_loss_number2(List<Integer> standard_loss_number2) {
        this.standard_loss_number2 = standard_loss_number2;
    }

    public List<Integer> getStandard_loss_number3() {
        return standard_loss_number3;
    }

    public void setStandard_loss_number3(List<Integer> standard_loss_number3) {
        this.standard_loss_number3 = standard_loss_number3;
    }

    public List<Integer> getStandard_loss_number6() {
        return standard_loss_number6;
    }

    public void setStandard_loss_number6(List<Integer> standard_loss_number6) {
        this.standard_loss_number6 = standard_loss_number6;
    }

    public List<Integer> getStandard_loss_number7() {
        return standard_loss_number7;
    }

    public void setStandard_loss_number7(List<Integer> standard_loss_number7) {
        this.standard_loss_number7 = standard_loss_number7;
    }

    public List<Integer> getStandard_loss_number8() {
        return standard_loss_number8;
    }

    public void setStandard_loss_number8(List<Integer> standard_loss_number8) {
        this.standard_loss_number8 = standard_loss_number8;
    }

    public List<Integer> getStandard_loss_number9() {
        return standard_loss_number9;
    }

    public void setStandard_loss_number9(List<Integer> standard_loss_number9) {
        this.standard_loss_number9 = standard_loss_number9;
    }

    public List<String> getResult_area() {
        return result_area;
    }

    public void setResult_area(List<String> result_area) {
        this.result_area = result_area;
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

    public String getKaizen_statrt_date() {
        return kaizen_statrt_date;
    }

    public void setKaizen_statrt_date(String kaizen_statrt_date) {
        this.kaizen_statrt_date = kaizen_statrt_date;
    }

    public String getKaizen_end_date() {
        return kaizen_end_date;
    }

    public void setKaizen_end_date(String kaizen_end_date) {
        this.kaizen_end_date = kaizen_end_date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getRev_details() {
        return rev_details;
    }

    public void setRev_details(String rev_details) {
        this.rev_details = rev_details;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getDate_of_implimentation() {
        return date_of_implimentation;
    }

    public void setDate_of_implimentation(String date_of_implimentation) {
        this.date_of_implimentation = date_of_implimentation;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getIdea_by() {
        return idea_by;
    }

    public void setIdea_by(String idea_by) {
        this.idea_by = idea_by;
    }

    public String getPresent_status() {
        return present_status;
    }

    public void setPresent_status(String present_status) {
        this.present_status = present_status;
    }

    public String getCountermeasure() {
        return countermeasure;
    }

    public void setCountermeasure(String countermeasure) {
        this.countermeasure = countermeasure;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }
}


