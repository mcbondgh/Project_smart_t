package com.smart.tableviewdata;

import java.sql.Timestamp;

public class UsersData {
    private  String email, username, password, role;
    private Timestamp dateCreated;

    public UsersData(String email, String username, String password, String role, Timestamp dateCreated) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.dateCreated = dateCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
