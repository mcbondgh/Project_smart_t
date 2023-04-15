package com.smart.models;

import com.smart.config.DbConnect;
import com.smart.tableviewdata.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.sql.*;
import java.time.LocalDate;

public class MainModel extends DbConnect {


    Statement statement;
    ResultSet resultSet;
    PreparedStatement prepare;
    protected int saveNewRole(String roleName) {
        int flag = 0;
        try {
            String insertQuery = "INSERT INTO roles VALUES(DEFAULT, '"+roleName+"', DEFAULT)";
            statement = CONNECTION().createStatement();
            flag = statement.executeUpdate(insertQuery);
            statement.close();
            CONNECTION().close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    protected ObservableList<RolesData> getRoles() throws SQLException {
        ObservableList<RolesData> data = FXCollections.observableArrayList();
        String select = "SELECT * FROM roles;";
        statement = CONNECTION().createStatement();
        resultSet = statement.executeQuery(select);
        while (resultSet.next()) {
            data.add(new RolesData(resultSet.getInt(1), resultSet.getString(2), resultSet.getTimestamp(3)));
        }
        return data ;
    }

    protected int deleteRole(int selectedItem){
        int flag = 0;
        try {
            statement = CONNECTION().createStatement();
            flag = statement.executeUpdate("DELETE FROM roles WHERE roleId = '"+selectedItem+"'");
            statement.close();
            CONNECTION().close();
        }catch (Exception ignored) {}
        return  flag;
    }

    protected int saveEmployee(String firstName, String lastName, String email, String mobileNumber, String gender, String department) {
        int flag =0;
        try {
            String insert = "INSERT INTO employees(firstName, lastName, email, mobileNumber, gender, department) VALUES(?, ?, ?, ?, ?, ?);";
            prepare  = CONNECTION().prepareStatement(insert);
            prepare.setString(1, firstName);
            prepare.setString(2, lastName);
            prepare.setString(3, email);
            prepare.setString(4, mobileNumber);
            prepare.setString(5, gender);
            prepare.setString(6, department);
            flag = prepare.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    protected  int saveUser(String username, String password, int roleId, int empId) {
        int flag = 0;
        try {
            String insert = "INSERT INTO users( empId, roleId, username, password) VALUES(?, ?, ?, ?)";
            prepare = CONNECTION().prepareStatement(insert);
            prepare.setInt(1, empId);
            prepare.setInt(2, roleId);
            prepare.setString(3, username);
            prepare.setString(4, password);
            flag = prepare.executeUpdate();

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    protected String getRoleIdByRoleName(String roleName) {
        String flag = "";
        try {
            String select = "SELECT roleId FROM roles WHERE role_name = '"+roleName+"'";
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery(select);
            if (resultSet.next()) {
                flag = resultSet.getString( 1);
            }
        }catch (Exception ignored){}
        return flag;
    }

    protected int getEmpIdByEmployeeName(String fullname) {
        int flag = 0;
        try {
            String select = "SELECt empId FROM employees where concat(firstName + \" \" + lastName);";
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery(select);
            flag = resultSet.getInt(1);
        }catch (Exception ignored){}
        return flag;
    }

    protected ObservableList<EmployeesData> getAllEmployees() {
        ObservableList<EmployeesData> data = FXCollections.observableArrayList();
        try {
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees");
            while(resultSet.next()) {
                int empId = resultSet.getInt(1);
                String firstname = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String mobileNumber = resultSet.getString("mobileNumber");
                String gender = resultSet.getString("gender");
                String department = resultSet.getString("department");
                Timestamp getTime = resultSet.getTimestamp("date_added");
                String fullname = firstname +" "+ lastName;
                data.addAll(new EmployeesData(empId, fullname, email, mobileNumber, gender, department, getTime));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return data;
    }

    protected ObservableList<UsersData> getAllUsers() {
        ObservableList<UsersData> data = FXCollections.observableArrayList();
        try {
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery("SELECT username, password, u.date_added, role_name, email FROM users as u\n" +
                    "JOIN roles as r ON\n" +
                    "\tu.roleId = r.roleId\n" +
                    "JOIN employees as e ON \n" +
                    "\tu.empId = e.empId;");

            while(resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String roleName = resultSet.getString("role_name");
                String email = resultSet.getString("email");
                Timestamp getTime = resultSet.getTimestamp("u.date_added");
                data.addAll( new UsersData(email, username, password, roleName, getTime));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }


    protected  int updateEmployee(int empID, String firstname, String lastname, String email, String gender, String department, String number) {
        int flag = 0;
        try {
            String update = "UPDATE EMPLOYEES SET firstName = ?, lastName = ?, email = ?, mobileNumber = ?, gender = ?, department = ? WHERE (empId = ?);";
            prepare = CONNECTION().prepareStatement(update);
            prepare.setString(1, firstname);
            prepare.setString(2, lastname);
            prepare.setString(3, email);
            prepare.setString(4, number);
            prepare.setString(5, gender);
            prepare.setString(6, department);
            prepare.setInt(7, empID);
            flag = prepare.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    protected int addVehicleCategory(String categoryName, String categoryClass, String description) {
        int flag = 0;
        try{
            String insert = "INSERT INTO vehicle_categories VALUES(DEFAULT, '"+categoryName+"', '"+categoryClass+"', '"+description+"', DEFAULT)";
            prepare = CONNECTION().prepareStatement(insert);
            flag = prepare.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    protected ObservableList<VehicleClassificationData> getVehicleCategory() {
        ObservableList<VehicleClassificationData> data = FXCollections.observableArrayList();
        try {
            String select = "SELECT * FROM vehicle_categories";
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery(select);
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String categoryName = resultSet.getString(2);
                String vehicleClass = resultSet.getString(3);
                String desc = resultSet.getString(4);
                Timestamp dateTime = resultSet.getTimestamp(5);
                data.addAll(new VehicleClassificationData(id, categoryName, vehicleClass, desc, dateTime));
            }
        }catch (Exception ignored){}
        return data;
    }

    protected int countTotalRegistration() {
        int data = 0;
        try {
            String select = "SELECT COUNT(*) FROM customer_registration";
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery(select);
            if (resultSet.next()) {
               data = resultSet.getInt(1);
            }
        }catch (Exception ignored){}
        return data;
    }

    protected int countTodayRegistration() {
        int data = 0;
        try {
            String select = "SELECT COUNT(*) FROM customer_registration WHERE DATE(date_added)  = CURRENT_DATE();";
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery(select);
            if (resultSet.next()) {
                data = resultSet.getInt(1);
            }
        }catch (Exception ignored){}
        return data;
    }

    protected int updateVehicleCategory(int id, String name, String desc, String catClass) {
        int flag = 0;
        try {
            String update = "UPDATE vehicle_categories SET category_name = ?, category_class = ?, description = ? WHERE (vehicleId = ?);";
            prepare = CONNECTION().prepareStatement(update);
            prepare.setString(1, name);
            prepare.setString(2, catClass);
            prepare.setString(3, desc);
            prepare.setInt(4, id);
            flag = prepare.executeUpdate();
            prepare.close();
            CONNECTION().close();
        }catch (Exception ignored){}
        return flag;
    }

    protected int addVehicleType(String vehicle_name, String vehicle_class, String vehicle_weight, int max_weight, int min_weight, double toll_rate) {
        int flag = 0;
        try {
            String insert = "INSERT INTO vehicle_types(vehicle_name, vehicle_class, vehicle_weight, max_weight, min_weight, toll_rate) VALUES(?, ?, ?, ?, ?, ?)";
            prepare = CONNECTION().prepareStatement(insert);
            prepare.setString(1, vehicle_name);
            prepare.setString(2, vehicle_class);
            prepare.setString(3, vehicle_weight);
            prepare.setInt(4, max_weight);
            prepare.setInt(5, min_weight);
            prepare.setDouble(6, toll_rate);
            flag = prepare.executeUpdate();
        }catch (Exception ignored) {}

        return flag;
    }

    protected  ObservableList<VehicleTypesData> getVehicleTypes() {
        ObservableList<VehicleTypesData> data = FXCollections.observableArrayList();

        try {
            String select = "SELECT * FROM vehicle_types ORDER BY vehicle_name DESC";
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery(select);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String vehicle_name = resultSet.getString("vehicle_name");
                String vehicle_class = resultSet.getString("vehicle_class");
                String vehicle_weight = resultSet.getString("vehicle_weight");
                int max_weight = resultSet.getInt("max_weight");
                int min_weight = resultSet.getInt("min_weight");
                double toll_rate = resultSet.getDouble("toll_rate");
                Timestamp date_added = resultSet.getTimestamp("date_added");
                data.addAll(new VehicleTypesData(id, vehicle_name, vehicle_class, vehicle_weight, max_weight, min_weight, toll_rate, date_added));
            }
        }catch (Exception ignored){}

        return data;
    }

    protected int addNewCustomer(String customer_name, String customer_contact, String customer_email, String customer_address, String customer_idType, String customer_idNumber, String comments, String customer_vehicle_name, String vehicle_class, String vehicle_weight, String registration_number, double toll_rate, String qr_code_token, LocalDate valid_date) {
        int flag = 0;
        try {
            String insert = "INSERT INTO customer_registration(customer_name, customer_contact, customer_email, customer_address, customer_idType, customer_idNumber, comments, customer_vehicle_name, vehicle_class, vehicle_weight, registration_number, toll_rate, qr_code_token, valid_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            prepare = CONNECTION().prepareStatement(insert);
            prepare.setString(1, customer_name);
            prepare.setString(2, customer_contact);
            prepare.setString(3, customer_email);
            prepare.setString(4, customer_address);
            prepare.setString(5, customer_idType);
            prepare.setString(6, customer_idNumber);
            prepare.setString(7, comments);
            prepare.setString(8, customer_vehicle_name);
            prepare.setString(9, vehicle_class);
            prepare.setString(10, registration_number);
            prepare.setString(11, vehicle_weight);
            prepare.setDouble(12, toll_rate);
            prepare.setString(13, qr_code_token);
            prepare.setDate(14, Date.valueOf(valid_date));
            flag = prepare.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return flag;
    }

    protected  ObservableList<CustomerRegistrationData> getCustomerRegistration() {
        ObservableList<CustomerRegistrationData> data = FXCollections.observableArrayList();

        try {
            String select = "SELECT * FROM customer_registration ORDER BY customer_name DESC";
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery(select);
            while(resultSet.next()) {
                int customerId = resultSet.getInt(1);
                String customer_name = resultSet.getString(2);
                String customer_contact = resultSet.getString(3);
                String customer_email = resultSet.getString(4);
                String customer_address = resultSet.getString(5);
                String customer_idType = resultSet.getString(6);
                String customer_idNumber = resultSet.getString(7);
                String comments = resultSet.getString(8);
                String customer_vehicle_name = resultSet.getString(9);
                String vehicle_class = resultSet.getString(10);
                String vehicle_weight = resultSet.getString(11);
                String registration_number = resultSet.getString(12);
                double toll_rate = resultSet.getDouble(13);
                String qr_code_token = resultSet.getString(14);
                Timestamp date_added =  resultSet.getTimestamp(15);
                LocalDate valid_date = resultSet.getDate("valid_date").toLocalDate();
                data.addAll(new CustomerRegistrationData(customerId, customer_name, customer_contact, customer_email, customer_address, customer_idType, customer_idNumber, comments,customer_vehicle_name, vehicle_class
                ,vehicle_weight, registration_number, toll_rate, qr_code_token, date_added, valid_date));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return data;
    }

    protected int addNewTollLog(String qr_token, double amount, long trans_id,  String payment_method, int payment_status) {
        int flag = 0;
        try{
            String insert = "INSERT INTO toll_logs(qr_token, amount, trans_id,  payment_method, payment_status) VALUES(?, ?, ?, ?, ?)";
            prepare = CONNECTION().prepareStatement(insert);
            prepare.setString(1,qr_token );
            prepare.setDouble(2, amount);
            prepare.setLong(3, trans_id);
            prepare.setString(4, payment_method);
            prepare.setInt(5, payment_status);
            flag = prepare.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    protected  ObservableList<TollLogsData> getTollLogs() {
        ObservableList<TollLogsData> data = FXCollections.observableArrayList();

        try {
            String select = "SELECT * FROM toll_logs";
            statement = CONNECTION().createStatement();
            resultSet = statement.executeQuery(select);
            while(resultSet.next()) {
                int log_id = resultSet.getInt("log_id");
                String qr_token = resultSet.getString("qr_token");
                double amount = resultSet.getDouble("amount");
                String payment_method = resultSet.getString("payment_method");
                int payment_status = resultSet.getInt("payment_status");
                long trans_id = resultSet.getLong("trans_id");
                Timestamp date_created = resultSet.getTimestamp("date_created");

                Text statusText = new Text("Pending");
                statusText.setStyle("-fx-fill:red; -fx-font-weight:bold; -fx-alignment:center");
                if (payment_status == 1) {
                    statusText.setText("PAID");
                    statusText.setStyle("-fx-fill:green; -fx-font-weight:bold; -fx-alignment:center");
                }

                data.addAll(new TollLogsData(log_id, qr_token, amount, trans_id, payment_method, statusText, date_created));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return data;
    }




}//end of class
