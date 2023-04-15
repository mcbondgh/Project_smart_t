package com.smart.tableviewdata;

import java.sql.Timestamp;

public class RolesData {
    private int roleId;
    private String roleName;
    private Timestamp dateAdded;

    public RolesData(int roleId, String roleName, Timestamp dateAdded) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.dateAdded = dateAdded;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
}
