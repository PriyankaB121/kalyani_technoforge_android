package com.operator.app.kalyanitechnoforge.Database;

public class Status {
    String Business_Id,Business_Info_Status,Contact_Info_Status,Business_Keyword_Status,Upload_Photo_Status;


    public Status(String Business_Id, String Business_Info_Status, String Contact_Info_Status, String Business_Keyword_Status, String Upload_Photo_Status){
        this.Business_Id=Business_Id;
        this.Business_Info_Status=Business_Info_Status;
        this.Contact_Info_Status=Contact_Info_Status;
        this.Business_Keyword_Status=Business_Keyword_Status;
        this.Upload_Photo_Status=Upload_Photo_Status;


    }
    public Status()
    {

    }

    public String getBusiness_Id() {
        return Business_Id;
    }

    public void setBusiness_Id(String business_Id) {
        Business_Id = business_Id;
    }

    public String getBusiness_Info_Status() {
        return Business_Info_Status;
    }

    public void setBusiness_Info_Status(String business_Info_Status) {
        Business_Info_Status = business_Info_Status;
    }

    public String getContact_Info_Status() {
        return Contact_Info_Status;
    }

    public void setContact_Info_Status(String contact_Info_Status) {
        Contact_Info_Status = contact_Info_Status;
    }

    public String getBusiness_Keyword_Status() {
        return Business_Keyword_Status;
    }

    public void setBusiness_Keyword_Status(String business_Keyword_Status) {
        Business_Keyword_Status = business_Keyword_Status;
    }

    public String getUpload_Photo_Status() {
        return Upload_Photo_Status;
    }

    public void setUpload_Photo_Status(String upload_Photo_Status) {
        Upload_Photo_Status = upload_Photo_Status;
    }
}
