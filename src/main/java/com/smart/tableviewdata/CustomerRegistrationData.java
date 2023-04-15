package com.smart.tableviewdata;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

public class CustomerRegistrationData {
    private int customerId;
    private String customer_name, customer_contact, customer_email, customer_address, customer_idType, customer_idNumber, comments, customer_vehicle_name, vehicle_class, vehicle_weight, registration_number;
    private double toll_rate;
    private String qr_code_token;
    private Timestamp date_added;
    LocalDate valid_date;


    public CustomerRegistrationData(int customerId, String customer_name, String customer_contact, String customer_email, String customer_address, String customer_idType, String customer_idNumber, String comments, String customer_vehicle_name, String vehicle_class, String vehicle_weight, String registration_number, double toll_rate, String qr_code_token, Timestamp date_added, LocalDate valid_date) {
        this.customerId = customerId;
        this.customer_name = customer_name;
        this.customer_contact = customer_contact;
        this.customer_email = customer_email;
        this.customer_address = customer_address;
        this.customer_idType = customer_idType;
        this.customer_idNumber = customer_idNumber;
        this.comments = comments;
        this.customer_vehicle_name = customer_vehicle_name;
        this.vehicle_class = vehicle_class;
        this.vehicle_weight = vehicle_weight;
        this.registration_number = registration_number;
        this.toll_rate = toll_rate;
        this.qr_code_token = qr_code_token;
        this.date_added = date_added;
        this.valid_date = valid_date;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_contact() {
        return customer_contact;
    }

    public void setCustomer_contact(String customer_contact) {
        this.customer_contact = customer_contact;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_idType() {
        return customer_idType;
    }

    public void setCustomer_idType(String customer_idType) {
        this.customer_idType = customer_idType;
    }

    public String getCustomer_idNumber() {
        return customer_idNumber;
    }

    public void setCustomer_idNumber(String customer_idNumber) {
        this.customer_idNumber = customer_idNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCustomer_vehicle_name() {
        return customer_vehicle_name;
    }

    public void setCustomer_vehicle_name(String customer_vehicle_name) {
        this.customer_vehicle_name = customer_vehicle_name;
    }

    public String getVehicle_class() {
        return vehicle_class;
    }

    public void setVehicle_class(String vehicle_class) {
        this.vehicle_class = vehicle_class;
    }

    public String getVehicle_weight() {
        return vehicle_weight;
    }

    public void setVehicle_weight(String vehicle_weight) {
        this.vehicle_weight = vehicle_weight;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public double getToll_rate() {
        return toll_rate;
    }

    public void setToll_rate(double toll_rate) {
        this.toll_rate = toll_rate;
    }

    public String getQr_code_token() {
        return qr_code_token;
    }

    public void setQr_code_token(String qr_code_token) {
        this.qr_code_token = qr_code_token;
    }

    public Timestamp getDate_added() {
        return date_added;
    }

    public void setDate_added(Timestamp date_added) {
        this.date_added = date_added;
    }

    public LocalDate getValid_date() {
        return valid_date;
    }

    public void setValid_date(LocalDate valid_date) {
        this.valid_date = valid_date;
    }





}
