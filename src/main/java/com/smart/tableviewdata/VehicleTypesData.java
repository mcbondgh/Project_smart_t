package com.smart.tableviewdata;

import java.sql.Timestamp;

public class VehicleTypesData {
        int id;
        String vehicle_name, vehicle_class, vehicle_weight;
        int max_weight, min_weight ;
        double toll_rate;
        Timestamp date_added;


    public VehicleTypesData(int id, String vehicle_name, String vehicle_class, String vehicle_weight, int max_weight, int min_weight, double toll_rate, Timestamp date_added) {
        this.id = id;
        this.vehicle_name = vehicle_name;
        this.vehicle_class = vehicle_class;
        this.vehicle_weight = vehicle_weight;
        this.max_weight = max_weight;
        this.min_weight = min_weight;
        this.toll_rate = toll_rate;
        this.date_added = date_added;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
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

    public int getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(int max_weight) {
        this.max_weight = max_weight;
    }

    public int getMin_weight() {
        return min_weight;
    }

    public void setMin_weight(int min_weight) {
        this.min_weight = min_weight;
    }

    public double getToll_rate() {
        return toll_rate;
    }

    public void setToll_rate(double toll_rate) {
        this.toll_rate = toll_rate;
    }

    public Timestamp getDate_added() {
        return date_added;
    }

    public void setDate_added(Timestamp date_added) {
        this.date_added = date_added;
    }
}
