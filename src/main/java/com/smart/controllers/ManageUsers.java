package com.smart.controllers;

import com.smart.alerts.UserAlerts;
import com.smart.models.MainModel;
import com.smart.tableviewdata.EmployeesData;
import com.smart.tableviewdata.RolesData;
import com.smart.tableviewdata.UsersData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManageUsers extends MainModel implements Initializable {

    UserAlerts alerts;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillGenderSelector();
        fillDepartmentSelector();
        fillEmployeesSelector();
        populateEmployeesTable();
        populateUsersTable();
        try {
            fillUserRole();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TableView<EmployeesData> employeesTableView;
    @FXML
    private TableColumn<EmployeesData, Timestamp> dateJoinedColumn;

    @FXML
    private TableColumn<EmployeesData, String> departmentColumn;
    @FXML
    private TableColumn<EmployeesData, String> fullnameColumn;
    @FXML
    private TableColumn<EmployeesData, String> genderColumn;

    @FXML
    private TableColumn<EmployeesData, String> numberColumn;
    @FXML
    private TableColumn<EmployeesData, String> emailColumn;

    @FXML
    private ComboBox<String> departmentSelector;
    @FXML
    private Button clearEmployeeButton;

    @FXML
    private Button clearUserButton;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> employeeSelector;


    @FXML
    private TextField firstnameField;

    @FXML
    private ComboBox<String> genderSelector, userRoleSelector;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField mobileNumberField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button saveEmployeeButton;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button saveUserButton;
    @FXML
    private TextField usernameField;


//    USERS TABLE VIEW ITEMS
    @FXML
    private TableView<UsersData> usersTableView;
    @FXML
    private TableColumn<UsersData, String> usernameColumn;
    @FXML
    private TableColumn<UsersData, String> rolesColumn;
    @FXML
    private TableColumn<UsersData, Timestamp> dateAddedColumn;


//    TRUE OR FALSE STATEMENTS IMPLEMENTATION....
    boolean isFirstNameEmpty() {
        return firstnameField.getText().isEmpty();
    }
    boolean isLastNameEmpty() {
        return lastnameField.getText().isEmpty();
    }
    boolean isMobileEmpty() {
        return mobileNumberField.getText().isEmpty();
    }
    boolean isEmailEmpty() {
        return emailField.getText().isBlank();
    }
    boolean isGenderEmpty() {
        return genderSelector.getValue().isEmpty();
    }
    boolean isDepartmentEmpty() {
        return departmentSelector.getValue().isEmpty();
    }

    boolean isRoleEmpty() {
        return userRoleSelector.getValue().isEmpty();
    }

    boolean isPasswordEmpty() {
        return passwordField.getText().isEmpty();
    }
    boolean isConfirmPasswordEmpty() {
        return confirmPasswordField.getText().isEmpty();
    }

    boolean isEmployeeEmpty() {
        return employeeSelector.getValue().isEmpty();
    }
    boolean checkPasswords() {
        return Objects.equals(passwordField.getText().toLowerCase(), confirmPasswordField.getText().toLowerCase());
    }




    //SPECIAL METHODS IMPLEMENTATION
    void fillGenderSelector() {
        genderSelector.getItems().addAll("Male", "Female", "Other");
    }
    void fillDepartmentSelector() {
        departmentSelector.getItems().addAll( "Client Attendant", "Front Dest", "Finance", "Human Resource", "Management", "Sanitation");
    }
    void fillUserRole() throws SQLException {
        for (RolesData item :getRoles()) {
            userRoleSelector.getItems().add(item.getRoleName());
        }
    }
    void fillEmployeesSelector() {
        for (EmployeesData values : getAllEmployees()) {
            employeeSelector.getItems().add(values.getFullname());
        }
    }
    void populateEmployeesTable() {
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        employeesTableView.setItems(getAllEmployees());
    }
    void populateUsersTable() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        rolesColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        dateAddedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        usersTableView.setItems(getAllUsers());
    }
    void refreshUserTable() {
        usersTableView.getItems().clear();
        usersTableView.setItems(getAllUsers());
    }
    void refreshEmployeesTable() {
        employeesTableView.getItems().clear();
        populateEmployeesTable();
    }
    void clearEmployeeFields() {
        firstnameField.clear();
        lastnameField.clear();
        emailField.clear();
        genderSelector.setValue(null);
        departmentSelector.setValue(null);
        mobileNumberField.clear();
    }
    void clearUsersField() {
        employeeSelector.setValue(null);
        usernameField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        saveUserButton.setDisable(true);
    }



    //ACTION EVENTS METHODS IMPLEMENTATION
    @FXML void checkMobileNumber(KeyEvent event) {
        int keylength = mobileNumberField.getLength();
        if (!(event.getCode().isDigitKey() || event.getCode().isArrowKey() || event.getCode().equals(KeyCode.BACK_SPACE))) {
            mobileNumberField.clear();
        } if (keylength > 10) {
            mobileNumberField.deleteText(10, keylength);
        }
    }
    @FXML void saveUserClicked() throws SQLException {
        int roleId = 0;
        int empId = 0;

        for (EmployeesData item : getAllEmployees()) {
            if (Objects.equals(employeeSelector.getValue(), item.getFullname())) {
                empId = item.getEmpId();
                break;
            }
        }
        for (RolesData item : getRoles()) {
            if (userRoleSelector.getValue().equals(item.getRoleName())) {
                roleId = item.getRoleId();
            }
        }

        alerts = new UserAlerts("SAVE USER", "ARE YOU SURE YOU WANT TO ADD SELECTED EMPLOYEE AS A USER?");
        if (alerts.confirmation()) {
            int flag = saveUser(usernameField.getText(), passwordField.getText(), roleId, empId);
            if (flag > 0) {
                refreshUserTable();
                clearUsersField();
            }
        }
    }
    @FXML void checkUserFields() {
        try {
            saveUserButton.setDisable(isEmployeeEmpty() || isPasswordEmpty() || isConfirmPasswordEmpty() || isRoleEmpty() || !checkPasswords());
        }catch (NullPointerException ignored){}
    }
    @FXML void clearUserClicked() {
        employeeSelector.setValue(null);
        usernameField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }
    @FXML void employeeSelected() {
        try {
            for (EmployeesData item : getAllEmployees()) {
                if (employeeSelector.getValue().equals(item.getFullname())) {
                    usernameField.setText(item.getEmail());
                    break;
                }
            }
        }catch (NullPointerException ignored){}
    }
    @FXML void saveEmployeeClicked() {
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String number = mobileNumberField.getText();
        String email = emailField.getText();
        String gender = genderSelector.getValue();
        String department = departmentSelector.getValue();
        int ID = employeesTableView.getSelectionModel().getSelectedItem().getEmpId();

        if (isMobileEmpty() || isFirstNameEmpty() || isLastNameEmpty() || isGenderEmpty() || isEmailEmpty() || isDepartmentEmpty()) {
            saveEmployeeButton.setDisable(true);
        } else {
            if (saveEmployeeButton.getText().equals("update")) {
                alerts = new UserAlerts("UPDATE EMPLOYEE", "ARE YOU SURE YOU WANT TO UPDATE '"+firstname + " "+ lastname +"' AS PART OF YOUR EMPLOYEES?");
                if (alerts.confirmation()) {
                    if (updateEmployee(ID, firstname, lastname, email, gender, department, number) > 0) {
                        refreshEmployeesTable();
                        clearEmployeeFields();
                    }
                }
            } else {
                saveEmployeeButton.setDisable(false);

                alerts = new UserAlerts("SAVE EMPLOYEE", "ARE YOU SURE YOU WANT TO SAVE '"+firstname + " "+ lastname +"' AS PART OF YOUR EMPLOYEES?");
                if (alerts.confirmation()) {
                    int flag = saveEmployee(firstname, lastname, email, number, gender, department);
                    if (flag > 0) {
                        refreshEmployeesTable();
                        clearEmployeeFields();
                    }
                }
            }
            }

    }
    @FXML void clearEmployeeClicked() {
            clearEmployeeFields();
    }
    @FXML void checkEmployeeInputFields() {
        try {
            saveEmployeeButton.setDisable(isFirstNameEmpty() || isLastNameEmpty() || isEmailEmpty() || isDepartmentEmpty() || isGenderEmpty() || isMobileEmpty() );
        }catch (NullPointerException ignored){}
    }

    @FXML void employeeTableClicked() {
        if (!employeesTableView.getSelectionModel().isEmpty()) {
            String[] fullname = employeesTableView.getSelectionModel().getSelectedItem().getFullname().split(" ");
            String firstname = fullname[0];
            String lastname = fullname[1];
            String mobile = employeesTableView.getSelectionModel().getSelectedItem().getMobileNumber();
            String email = employeesTableView.getSelectionModel().getSelectedItem().getEmail();
            String gender = employeesTableView.getSelectionModel().getSelectedItem().getGender();
            String department = employeesTableView.getSelectionModel().getSelectedItem().getDepartment();


            firstnameField.setText(firstname);
            lastnameField.setText(lastname);
            mobileNumberField.setText(mobile);
            emailField.setText(email);
            genderSelector.setValue(gender);
            departmentSelector.setValue(department);
            saveEmployeeButton.setText("update");
            saveEmployeeButton.setDisable(false);

        }
    }



}//END OF CLASS
