package com.smart.tableviewdata;

import java.sql.Timestamp;

public class EmployeesData {
    private int empId;
    private  String fullname, email, mobileNumber, gender, department;
    Timestamp dateCreated;


    public EmployeesData(int empId, String fullname, String email, String mobileNumber, String gender, String department, Timestamp dateCreated) {
        this.empId = empId;
        this.fullname = fullname;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.department = department;
        this.dateCreated = dateCreated;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
