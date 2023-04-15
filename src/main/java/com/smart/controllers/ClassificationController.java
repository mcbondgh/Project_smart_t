package com.smart.controllers;

import com.smart.alerts.UserAlerts;
import com.smart.models.MainModel;
import com.smart.tableviewdata.UsersData;
import com.smart.tableviewdata.VehicleClassificationData;
import com.smart.tableviewdata.VehicleTypesData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class ClassificationController extends MainModel implements Initializable {

    UserAlerts alerts;


        public void initialize(URL url, ResourceBundle resourceBundle) {
            fillCassSelector();
            fillVehicleClassSelector();
            populateVehiclesTable();
            fillWeightSelector();
            populateVehicleTypeTable();
        }

    @FXML
    private TableView<VehicleClassificationData> categoriesTableView;

    @FXML
    private ComboBox<String> categoryClassSelector;

    @FXML
    private TableColumn<VehicleClassificationData, Integer> categoryIdColumn;

    @FXML
    private TableColumn<VehicleClassificationData, String> categoryNameColumn;

    @FXML
    private TextField categoryNameField;

    @FXML
    private TableColumn<VehicleClassificationData, String> classColumn;

    @FXML
    private Button clearCategoryButton;

    @FXML
    private Button clearVehicleButton;

    @FXML
    private TableColumn<VehicleClassificationData, Timestamp> dateAddedColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TableColumn<VehicleTypesData, Integer> idTypeColumn;

    @FXML
    private TableColumn<VehicleTypesData, Integer> maxWeighColumn;

    @FXML
    private TextField maxWeightField;

    @FXML
    private TableColumn<VehicleTypesData, Integer> minWeightColumn;

    @FXML
    private TextField minWeightField;

    @FXML
    private Button saveCategoryButton;

    @FXML
    private Button saveVehicleButton;

    @FXML
    private TableColumn<VehicleTypesData, Double> tollRateColumn;

    @FXML
    private TextField tollRateField;

    @FXML
    private TableColumn<VehicleTypesData, Timestamp> vehicleAddedDateColumn;

    @FXML
    private TableColumn<VehicleTypesData, String> vehicleClassColumn;

    @FXML
    private ComboBox<String> vehicleClassSelector;

    @FXML
    private TextField vehicleNameField;

    @FXML
    private TableView<VehicleTypesData> vehicleTableView;

    @FXML
    private TableColumn<?, ?> vehicleTypeColumn;
    @FXML
    private TableColumn<?, ?> weightCategoryColumn;

    @FXML
    private ComboBox<String> weightSelector;

//    IMPLEMENTATION OF SPECIAL METHODS.

    void populateVehiclesTable() {
        categoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleClass"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateAddedColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
        categoriesTableView.setItems(getVehicleCategory());
    }

    void refreshVehicleCategoryTable() {
        vehicleTableView.getItems().clear();
        populateVehiclesTable();
    }
        void fillCassSelector() {
            categoryClassSelector.getItems().addAll("A", "B", "C", "D", "E", "F");
        }
        void fillVehicleClassSelector() {
            vehicleClassSelector.getItems().addAll("A", "B", "C", "D", "E", "F");
        }
        void fillWeightSelector() {
            weightSelector.getItems().addAll("LIGHT", "MEDIUM", "HEAVY");
        }

        void clearCategoryFields() {
            categoryNameField.clear();
            categoryClassSelector.setValue(null);
            descriptionField.clear();
            saveCategoryButton.setDisable(true);
        }
        void clearVehicleWeightField() {
            vehicleNameField.clear();
            vehicleClassSelector.setValue(null);
            weightSelector.setValue(null);
            maxWeightField.clear();
            minWeightField.clear();
            tollRateField.clear();
            saveVehicleButton.setDisable(true);
        }

        void refreshVehicleTypeTable () {
            vehicleTableView.getItems().clear();
            vehicleTableView.setItems(getVehicleTypes());
        }

        void populateVehicleTypeTable() {
            vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle_name"));
            idTypeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            vehicleClassColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle_class"));
            weightCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle_weight"));
            minWeightColumn.setCellValueFactory(new PropertyValueFactory<>("min_weight"));
            maxWeighColumn.setCellValueFactory(new PropertyValueFactory<>("max_weight"));
            tollRateColumn.setCellValueFactory(new PropertyValueFactory<>("toll_rate"));
            vehicleAddedDateColumn.setCellValueFactory(new PropertyValueFactory<>("date_added"));
            vehicleTableView.setItems(getVehicleTypes());
        }



//        IMPLEMENTATION OF TRUE OR FALSE STATEMENTS
    boolean isNameFieldEmpty() {
            return categoryNameField.getText().isEmpty();
    }
    boolean isClassSelectorEmpty() {
            return categoryClassSelector.getValue() == null;
    }
    boolean isVehicleNameEmpty() {
        return vehicleNameField.getText().isEmpty();
    }
    boolean isVehicleClassSelectorEmpty() {
        return vehicleClassSelector.getValue().isEmpty();
    }
    boolean isWeightSelectorEmpty() {
        return weightSelector.getValue().isEmpty();
    }
    boolean isMinWeighEmpty() {
        return minWeightField.getText().isEmpty();
    }
    boolean isMaxWeighEmpty() {
        return maxWeightField.getText().isEmpty();
    }
    boolean isTollRateField() {
        return tollRateField.getText().isEmpty();
    }

// ACTION EVENTS
    @FXML void checkInputFields() {
        saveCategoryButton.setDisable(isNameFieldEmpty() || isClassSelectorEmpty());
    }

    @FXML void saveCategoryButtonClicked() {
        String name = categoryNameField.getText();
        String vehicleClass = categoryClassSelector.getValue();
        String description = descriptionField.getText();
        int id = categoriesTableView.getSelectionModel().getSelectedItem().getCategoryId();

        if (saveCategoryButton.getText().equals("Update")) {
            alerts = new UserAlerts("UPDATE VEHICLE CATEGORY", "ARE YOU SURE YOU WANT TO UPDATE VEHICLE CATEGORY?");
            if (alerts.confirmation()) {
                if (updateVehicleCategory(id, name, description, vehicleClass) > 0) {
                    refreshVehicleCategoryTable();
                    clearCategoryFields();
                }
            }
        } else {
            alerts = new UserAlerts("SAVE VEHICLE CATEGORY", "ARE YOU SURE YOU WANT TO SAVE VEHICLE CATEGORY?");
            if (alerts.confirmation()) {
                if(addVehicleCategory(name, vehicleClass, description) > 0) {
                    refreshVehicleCategoryTable();
                    clearCategoryFields();
                }
            }
        }
    }

    @FXML void clearCategoryButtonClicked() {
        clearCategoryFields();
    }

    @FXML void categoriesTableViewClicked(MouseEvent event) {
        if (!categoriesTableView.getItems().isEmpty()) {
            int id = categoriesTableView.getSelectionModel().getSelectedItem().getCategoryId();
            if (event.getClickCount() == 2) {
                System.out.println(id);
            }else {
                String name = categoriesTableView.getSelectionModel().getSelectedItem().getCategoryName();
                String desc  = categoriesTableView.getSelectionModel().getSelectedItem().getDescription();
                String catClass = categoriesTableView.getSelectionModel().getSelectedItem().getVehicleClass();
                saveCategoryButton.setText("Update");
                categoryNameField.setText(name);
                descriptionField.setText(desc);
                categoryClassSelector.setValue(catClass);
            }
        }
    }

    @FXML void validateMaxWeightValue(KeyEvent event) {
        if (!(event.getCode().isDigitKey() || event.getCode().isArrowKey() || event.getCode().equals(KeyCode.BACK_SPACE))) {
            maxWeightField.clear();
        }
    }
    @FXML void validateMinWeightValue(KeyEvent event) {
        if (!(event.getCode().isDigitKey() || event.getCode().isArrowKey() || event.getCode().equals(KeyCode.BACK_SPACE))) {
            minWeightField.clear();
        }
    }

    @FXML void validateTollRateValue(KeyEvent event) {
        if (!(event.getCode().isDigitKey() || event.getCode().isArrowKey() || event.getCode().equals(KeyCode.BACK_SPACE) || event.getCode().equals(KeyCode.PERIOD))) {
            tollRateField.clear();
        }
    }

    @FXML void checkAllWeightFields() {
        saveVehicleButton.setDisable(isVehicleNameEmpty() || isVehicleClassSelectorEmpty() || isMaxWeighEmpty() || isMinWeighEmpty() || isTollRateField() || isWeightSelectorEmpty());
    }

    @FXML void saveVehicleButtonClicked() {
        String name = vehicleNameField.getText();
        String vehicleClass = vehicleClassSelector.getValue();
        String weightType = weightSelector.getValue();
        int max = Integer.parseInt(maxWeightField.getText());
        int min = Integer.parseInt(minWeightField.getText());
        double tollRate = Double.parseDouble(tollRateField.getText());

        alerts = new UserAlerts("SAVE VEHICLE", "ARE YOU SURE YOU WANT TO SAVE THIS VEHICLE TYPE?");
        if (alerts.confirmation()) {
            if (addVehicleType(name, vehicleClass, weightType, max, min, tollRate) > 0) {
                refreshVehicleTypeTable();
                clearVehicleWeightField();
            }
        }
    }

}
