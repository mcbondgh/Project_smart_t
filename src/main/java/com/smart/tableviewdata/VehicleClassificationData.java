package com.smart.tableviewdata;

import java.sql.Timestamp;

public class VehicleClassificationData {
    private int categoryId;
    private String categoryName;

    private String description;
    private String vehicleClass;
    private Timestamp dateAdded;

    public VehicleClassificationData(int categoryId, String categoryName, String vehicleClass, String description, Timestamp dateAdded) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.vehicleClass = vehicleClass;
        this.description = description;
        this.dateAdded = dateAdded;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
}
