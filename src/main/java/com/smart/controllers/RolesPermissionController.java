package com.smart.controllers;

import com.smart.alerts.UserAlerts;
import com.smart.config.DbConnect;
import com.smart.models.MainModel;
import com.smart.tableviewdata.RolesData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ValueAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RolesPermissionController extends MainModel implements Initializable {

        public void initialize(URL url, ResourceBundle resourceBundle) {
                try {
                        populateRolesTableView();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

        UserAlerts alerts;

//    FXML NODE EJECTION

        @FXML
        private TableView<RolesData> rolesTableView;
        @FXML
        private TableColumn<RolesData, Integer> roleIdColumn;

        @FXML
        private TableColumn<RolesData, String> roleNameColumn;

        @FXML
        private TextField roleTextField;

        @FXML
        private Button saveRoleButton;
        @FXML private ComboBox<String> userRoleSelector;



//  TRUE OR FALSE STATEMENTS
        boolean isRolesFieldEmpty() {
                return roleTextField.getText().isEmpty();
        }




//  IMPLEMENTATION OF SPECIAL METHODS

        public void populateRolesTableView() throws SQLException {
                roleIdColumn.setCellValueFactory(new PropertyValueFactory<>("roleId"));
                roleNameColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));
                rolesTableView.setItems(getRoles());
        }


        public void refreshRolesTable() throws SQLException {
                rolesTableView.getItems().clear();
                rolesTableView.setItems(getRoles());

        }





//    IMPLEMENTATION OF ACTION EVENT METHODS
        @FXML void saveRoleButtonClicked() throws SQLException {
                alerts = new UserAlerts("SAVE NEW ROLE", "ARE YOU SURE YOU WANT TO SAVE '" + roleTextField.getText()+ "' AS NEW ROLE?", "please confirm YES to save, else CANCEL");
                if (alerts.confirmation()) {
                        if (saveNewRole(roleTextField.getText()) > 0) {
                                refreshRolesTable();
                                saveRoleButton.setDisable(true);
                                roleTextField.setText(null);
                        }
                }
        }

        @FXML void inputFieldOnTyped() {
                saveRoleButton.setDisable(isRolesFieldEmpty());

        }
        @FXML void rolesTableDoubleClicked(MouseEvent event) throws SQLException {
                int selectedItem = rolesTableView.getSelectionModel().getSelectedItem().getRoleId();
                String roleName = rolesTableView.getSelectionModel().getSelectedItem().getRoleName();
                if (event.getClickCount() == 2) {
                        alerts = new UserAlerts("DELETE", "DO YOU WANT TO DELETE USER ROLE '"+roleName+"'", "please confirm YES to delete else CANCEL" );
                        if (alerts.confirmation()) {
                                if(deleteRole(selectedItem) > 0) {
                                        refreshRolesTable();
                                }

                        }
                }
        }
        @FXML void userRoleSelectorClicked() throws SQLException {
                userRoleSelector.getItems().clear();
                for (RolesData item : getRoles()) {
                        userRoleSelector.getItems().add(item.getRoleName());
                }
        }


}//end of class

