package com.smart.tableviewdata;

import javafx.scene.text.Text;

import java.sql.Timestamp;

public class TollLogsData {

    int log_id;
    String qr_token;
    double amount;
    long  trans_id;
    String payment_method;
    Text payment_status;
    Timestamp date_created;

    public TollLogsData(int log_id, String qr_token, double amount, long trans_id, String payment_method, Text payment_status, Timestamp date_created) {
        this.log_id = log_id;
        this.qr_token = qr_token;
        this.amount = amount;
        this.trans_id = trans_id;
        this.payment_method = payment_method;
        this.payment_status = payment_status;
        this.date_created = date_created;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getQr_token() {
        return qr_token;
    }

    public void setQr_token(String qr_token) {
        this.qr_token = qr_token;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public long getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(long trans_id) {
        this.trans_id = trans_id;
    }

    public Text getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(Text payment_status) {
        this.payment_status = payment_status;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }
}
