package com.operator.app.kalyanitechnoforge.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("last_login")
    private String last_login;
    @SerializedName("employee_no")
    private String employee_no;
    @SerializedName("name")
    private String name;
    @SerializedName("mobile")
    private String mobile;

    @SerializedName("email")
    private String email;
    @SerializedName("role")
    private String role;

    @SerializedName("password")
    private String password;
    @SerializedName("otp")
    private String otp;

    @SerializedName("department")
    private String department;

    @SerializedName("division")
    private String division;

    @SerializedName("is_deleted")
    private String is_deleted;

    @SerializedName("is_active")
    private String is_active;

    @SerializedName("is_staff")
    private String is_staff;

    @SerializedName("is_superuser")
    private String is_superuser;

    @SerializedName("created_by")
    private String created_by;

    @SerializedName("updated_by")
    private String updated_by;

    @SerializedName("created_datetime")
    private String created_datetime;


    @SerializedName("updated_datetime")
    private String updated_datetime;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getEmployee_no() {
        return employee_no;
    }

    public void setEmployee_no(String employee_no) {
        this.employee_no = employee_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(String is_staff) {
        this.is_staff = is_staff;
    }

    public String getIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(String is_superuser) {
        this.is_superuser = is_superuser;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getCreated_datetime() {
        return created_datetime;
    }

    public void setCreated_datetime(String created_datetime) {
        this.created_datetime = created_datetime;
    }

    public String getUpdated_datetime() {
        return updated_datetime;
    }

    public void setUpdated_datetime(String updated_datetime) {
        this.updated_datetime = updated_datetime;
    }
}
