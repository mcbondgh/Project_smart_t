package com.smart.controllers;

import com.google.zxing.WriterException;
import com.smart.alerts.UserAlerts;
import com.smart.models.MainModel;
import com.smart.specialmethods.QRGenerator;
import com.smart.tableviewdata.CustomerRegistrationData;
import com.smart.tableviewdata.VehicleTypesData;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerRegistrationController extends MainModel implements Initializable {
    QRGenerator qrGenerator;
    UserAlerts alerts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillIdTypeSelector();
        updateRegistrationGUI();
        fillCustomersTable();
//        fillClassSelector();
        fillVehicleNameSelector();
//        fillVehicleWeight();
    }



//    FXML FILE EJECTIONS

    @FXML
    private TextField customerNameField;
    @FXML
    private TextField vehicleClassField;

    @FXML
    private Button clearCustomerButton;
    @FXML
    private TextField digitalAddressField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField emailAddressField;

    @FXML
    private Button generateQrButton;

    @FXML
    private TextField idNumberField;
    @FXML
    private ComboBox<String> idTypeSelector;
    @FXML
    private TextField mobileNumberField;
    @FXML
    private ImageView qrImageView;

    @FXML
    private TextField registrationNumberField;
    @FXML
    private Button saveCustomerButton;

    @FXML
    private TextField searchCustomerField;

    @FXML
    private CheckBox sendSMSCheckBox;

    @FXML
    private Label todayRegistrationLabel;

    @FXML
    private Label tollRateLabel;

    @FXML
    private Label totalRegistrationLabel, qrCodeName;
    @FXML
    private TextField vehicleWeightField;



//    TABLE VIEW NODES


    @FXML
    private TableView<CustomerRegistrationData> customerRegistrationTable;
    @FXML
    private TableColumn<CustomerRegistrationData, String> customerNameColumn;
    @FXML
    private TableColumn<CustomerRegistrationData, String>  dateAddreColumn;
    @FXML
    private TableColumn<CustomerRegistrationData, String>  digitalAddressColumn;
    @FXML
    private TableColumn<CustomerRegistrationData, String>  emailAddressColumn;
    @FXML
    private TableColumn<CustomerRegistrationData, String>  idNumbreColumn;
    @FXML
    private TableColumn<CustomerRegistrationData, String>  idTypeColumn;
    @FXML
    private TableColumn<CustomerRegistrationData, String>  mobileNumberColumn;

    @FXML private TableColumn<CustomerRegistrationData, String>  valid_date_Column;
    @FXML
    private TableColumn<CustomerRegistrationData, String>  registrationColumn;

    @FXML
    private TableColumn<CustomerRegistrationData, String>  vehicleClassColumn;

    @FXML
    private TableColumn<CustomerRegistrationData, String>  vehicleNameColumn;

    @FXML
    private MFXFilterComboBox<String> vehicleNameSelector;

    @FXML
    private TableColumn<CustomerRegistrationData, String>  vehicleWeightColumn;



//    SPECIAL METHODS IMPLEMENTATION
    void fillIdTypeSelector() {
        idTypeSelector.getItems().addAll("Ghana Card", "NHIS", "Passport", "Voter Id");
    }

    void fillVehicleNameSelector() {
        for (VehicleTypesData item : getVehicleTypes()) {
            vehicleNameSelector.getItems().add(item.getVehicle_name());
        }
    }

    void clearCustomerRegistrationFields(){
        customerNameField.clear();
        mobileNumberField.clear();
        idNumberField.clear();
        idTypeSelector.setValue(null);
        emailAddressField.clear();
        descriptionField.clear();
        vehicleNameSelector.setValue(null);
        vehicleClassField.clear();
        vehicleWeightField.clear();
        registrationNumberField.clear();
        tollRateLabel.setText(String.valueOf(0.00));
        qrCodeName.setText(null);
        saveCustomerButton.setDisable(true);
        generateQrButton.setDisable(true);
        qrImageView.setImage(null);
    }


//    TRUE OR FALSE METHODS IMPLEMENTATION
    boolean isCustomerFieldEmpty() {
        return customerNameField.getText().isEmpty();
    }
    boolean isMobileEmpty() {
        return mobileNumberField.getText().isEmpty();
    }
    boolean isDigitalAddressFieldEmpty() {
        return digitalAddressField.getText().isEmpty();
    }
    boolean isEmailEmpty() {
        return emailAddressField.getText().isEmpty();
    }
    boolean isIdTypeEmpty() {
        return idTypeSelector.getValue().isEmpty();
    }
    boolean isIdNumberFieldEmpty() {
        return idNumberField.getText().isEmpty();
    }
    boolean isVehicleNameSelector() {
        return vehicleNameSelector.getValue().isEmpty();
    }
    boolean isClassSelectorEmpty() {
        return vehicleClassField.getText().isEmpty();
    }
    boolean isRegistrationNumberEmpty() {
        return registrationNumberField.getText().isEmpty();
    }
    boolean isVehicleWeightEmpty() {
        return vehicleWeightField.getText().isEmpty();
    }


    void fillCustomersTable() {
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("customer_contact"));
        digitalAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        idTypeColumn.setCellValueFactory(new PropertyValueFactory<>("customer_idType"));
        idNumbreColumn.setCellValueFactory(new PropertyValueFactory<>("customer_idNumber"));
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customer_email"));
        vehicleNameColumn.setCellValueFactory(new PropertyValueFactory<>("customer_vehicle_name"));
        vehicleClassColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle_class"));
        registrationColumn.setCellValueFactory(new PropertyValueFactory<>("registration_number"));
        vehicleWeightColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle_weight"));
        dateAddreColumn.setCellValueFactory(new PropertyValueFactory<>("date_added"));
        valid_date_Column.setCellValueFactory(new PropertyValueFactory<>("valid_date"));
        customerRegistrationTable.setItems(getCustomerRegistration());
    }
    void refreshCustomersTable() {
        customerRegistrationTable.getItems().clear();
        fillCustomersTable();
    }

    void updateRegistrationGUI() {
        totalRegistrationLabel.setText(String.valueOf(countTotalRegistration()));
        todayRegistrationLabel.setText(String.valueOf(countTodayRegistration()));
    }

    @FXML void filterRegistrationTable() {
        try {
            customerRegistrationTable.getItems().clear();
            FilteredList<CustomerRegistrationData> filteredList =  new FilteredList<>(getCustomerRegistration(), p -> true);
            searchCustomerField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(customerRegistrationData -> {
                    if (newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }
                    String searchKeyWord = newValue.toLowerCase();
                    if (customerRegistrationData.getCustomer_name().toLowerCase().contains(searchKeyWord)) {
                        return true;
                    } else if (customerRegistrationData.getCustomer_vehicle_name().toLowerCase().equals(searchKeyWord)) {
                        return true;
                    }
                    return true;
                });
            });
            SortedList<CustomerRegistrationData> sortedResult = new SortedList<>(filteredList);
            sortedResult.comparatorProperty().bind(customerRegistrationTable.comparatorProperty());
            customerRegistrationTable.setItems(sortedResult);
        }catch (Exception ignored) {}
    }








//    ACTION EVENT METHODS IMPLEMENTATION

    @FXML
    void clearButtonClicked(ActionEvent event) {
        clearCustomerRegistrationFields();
    }

    @FXML
    void generateQrButtonClicked(ActionEvent event) throws IOException, WriterException {
        String customerName = customerNameField.getText();
        String registrationNo = registrationNumberField.getText();
        String vehicleClass = vehicleClassField.getText();
        String vehicleType = vehicleNameSelector.getValue();
        double tollRate = Double.parseDouble(tollRateLabel.getText());
        qrGenerator = new QRGenerator(customerName, registrationNo, vehicleType, vehicleClass, tollRate, qrImageView);
        qrCodeName.setText(qrGenerator.generateQRCode());
        generateQrButton.setDisable(true);
    }

    @FXML
    void saveCustomerButtonClicked(ActionEvent event) {
        String name = customerNameField.getText();
        String number = mobileNumberField.getText();
        String email = emailAddressField.getText();
        String digital = digitalAddressField.getText();
        String idSelector = idTypeSelector.getValue();
        String idNumber = idNumberField.getText();
        String desc = descriptionField.getText();
        String vehicleSelector = vehicleNameSelector.getValue();
        String vheClass = vehicleClassField.getText();
        String weight = vehicleWeightField.getText();
        String registration = registrationNumberField.getText();
        String qr_name = qrCodeName.getText();
        double  toll_rate =Double.parseDouble (tollRateLabel.getText());
        LocalDate valid_date = LocalDate.now().plusMonths(1);
        alerts = new UserAlerts("SAVE CUSTOMER", "ARE YOU SURE YOU WANT TO SAVE THIS CUSTOMER? ");
        if (alerts.confirmation()) {
            if (addNewCustomer(name, number, email, digital, idSelector, idNumber, desc, vehicleSelector, vheClass, weight, registration, toll_rate,qr_name, valid_date) > 0) {
                clearCustomerRegistrationFields();
                updateRegistrationGUI();
                refreshCustomersTable();
            }
        }
    }

    @FXML void validateMobileNumberField(KeyEvent event) {
        int keylength = mobileNumberField.getLength();
        if (!(event.getCode().isDigitKey() || event.getCode().isArrowKey() || event.getCode().equals(KeyCode.BACK_SPACE))) {
            mobileNumberField.clear();
        } if (keylength > 10) {
            mobileNumberField.deleteText(10, keylength);
        }
    }

    @FXML void checkAllInputFields() {
        generateQrButton.setDisable(isCustomerFieldEmpty() || isIdNumberFieldEmpty() || isRegistrationNumberEmpty() || isIdNumberFieldEmpty() || isEmailEmpty() || isIdTypeEmpty() || isMobileEmpty() || isVehicleNameSelector() || isVehicleWeightEmpty());
        saveCustomerButton.setDisable(isCustomerFieldEmpty() || isIdNumberFieldEmpty() || isRegistrationNumberEmpty() || isIdNumberFieldEmpty() || isEmailEmpty() || isIdTypeEmpty() || isMobileEmpty() || isVehicleNameSelector() || isVehicleWeightEmpty());
    }


    @FXML void vehicleNameSelected() {
        for (VehicleTypesData item : getVehicleTypes()) {
            if (vehicleNameSelector.getValue().equals(item.getVehicle_name())) {
                vehicleClassField.setText(item.getVehicle_class());
                vehicleWeightField.setText(item.getVehicle_weight());
                tollRateLabel.setText(String.valueOf(item.getToll_rate()));
            }
        }
    }



}//end of class
