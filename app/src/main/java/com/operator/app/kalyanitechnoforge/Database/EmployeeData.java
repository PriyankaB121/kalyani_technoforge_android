package com.operator.app.kalyanitechnoforge.Database;

/**
 */

public class EmployeeData {
    String Employee_Id,Employee_Name,Employee_Mobileno,Employee_Email;


    public EmployeeData(String Employee_Id, String Employee_Name, String Employee_Mobileno,  String Employee_Email){
        this.Employee_Id=Employee_Id;
        this.Employee_Name=Employee_Name;
        this.Employee_Mobileno=Employee_Mobileno;
       // this.Employee_City=Employee_City;
        this.Employee_Email=Employee_Email;
        //this.Image=Image;

    }
    public EmployeeData()
    {

    }

    public String getEmployee_Id() {
        return Employee_Id;
    }

    public void setEmployee_Id(String employee_Id) {
        Employee_Id = employee_Id;
    }

    public String getEmployee_Name() {
        return Employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        Employee_Name = employee_Name;
    }

    public String getEmployee_Mobileno() {
        return Employee_Mobileno;
    }

    public void setEmployee_Mobileno(String employee_Mobileno) {
        Employee_Mobileno = employee_Mobileno;
    }


    public String getEmployee_Email() {
        return Employee_Email;
    }

    public void setEmployee_Email(String employee_Email) {
        Employee_Email = employee_Email;
    }


}
