package com.smart.config;
import com.smart.tableviewdata.EmployeesData;
import com.smart.tableviewdata.RolesData;
import com.smart.tableviewdata.UsersData;
import javafx.beans.NamedArg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbConnect {

    protected Connection CONNECTION() throws SQLException {
        String PASSWORD = "12345";
        String URL = "jdbc:mysql://127.0.0.1:3308/smart_toll";
        String DATABASE_NAME = "smart_toll";
        return DriverManager.getConnection(URL, DATABASE_NAME, PASSWORD);
    }


}//END OF CLASS
