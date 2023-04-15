package com.smart.controllers;

import com.google.zxing.NotFoundException;
import com.smart.alerts.UserAlerts;
import com.smart.models.MainModel;
import com.smart.specialmethods.QRGenerator;
import com.smart.tableviewdata.CustomerRegistrationData;
import com.smart.tableviewdata.TollLogsData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class TollPayments extends MainModel implements Initializable {


    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTollLogsTable();
    }

    QRGenerator qrGenerator;
    UserAlerts alerts;
    @FXML
    private CheckBox cashCheckBox, allPaymentCheckBox;
    @FXML
    private TextField cashField;
    @FXML
    private TableView<TollLogsData> tollLogsTableView;
    @FXML
    private TableColumn<TollLogsData, Integer> paymentIdColumn;
    @FXML
    private TableColumn<TollLogsData, String> tokenColumn;
    @FXML
    private Button chooseImage, clearQrButton;

    @FXML
    private TableColumn<TollLogsData, Double> transIdColumn;
    @FXML
    private TableColumn<TollLogsData, Double> amountColumn;
    @FXML
    private TableColumn<TollLogsData, Text> paymentStatusColumn;
    @FXML
    private Label dateLabel, displayTotalLabel;
    @FXML
    private TextArea descriptionBox;
    @FXML
    private TableColumn<TollLogsData, String> paymentModeColumn;
    @FXML
    private TableColumn<TollLogsData, Timestamp> dateAddedColumn;
    @FXML
    private Button makePaymentButton;
    @FXML
    private CheckBox momoCheckBox;
    @FXML
    private TextField momoField;
    @FXML
    private Label payableLabel;
    @FXML
    private Label qrCodeName;
    @FXML
    private ImageView qrImageView;
    @FXML
    private Button scanImage, validateQrButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label tollRateLabel;
    @FXML
    private TextField transactionIdFied;
    File selectedFile;
    String fileName, filePath;




//    TRUE OR FALSE STATEMENTS AND THEIR IMPLEMENTATION
    boolean isMomoChecked() {
        return momoCheckBox.isSelected();
    }
    boolean isCashChecked() {
        return cashCheckBox.isSelected();
    }
    boolean isAllPaymentChecked() {
        return allPaymentCheckBox.isSelected();
    }
    boolean isQrCodeNameEmpty() {
        return qrCodeName.getText().isEmpty() || qrCodeName.getText().isBlank() ;
    }




//    OTHER METHODS IMPLEMENTATION
    void populateTollLogsTable() {
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("log_id"));
        tokenColumn.setCellValueFactory(new PropertyValueFactory<>("qr_token"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentModeColumn.setCellValueFactory(new PropertyValueFactory<>("payment_method"));
        paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        dateAddedColumn.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        transIdColumn.setCellValueFactory(new PropertyValueFactory<>("trans_id"));
        tollLogsTableView.setItems(getTollLogs());
    }

    void refreshTollLogs() {
        tollLogsTableView.getItems().clear();
        populateTollLogsTable();
    }

    void calculatePaymentAmount() {
        try {
            double cash;
            double momo;
            double total;
            if (isAllPaymentChecked()) {
                if (cashField.getText().isEmpty() || momoField.getText().isEmpty()) {
                    displayTotalLabel.setText("0.00");
                } else {
                    cash = Integer.parseInt(cashField.getText());
                    momo = Integer.parseInt(momoField.getText());
                    total = cash + momo;
                    displayTotalLabel.setText(String.valueOf(total));
                }

            } else if (isCashChecked()) {
                displayTotalLabel.setText(String.valueOf(cashField.getText()));

            } else {
                displayTotalLabel.setText(String.valueOf(momoField.getText()));
            }
        }catch (NumberFormatException ex) {
            displayTotalLabel.setText(String.valueOf(0.00));
        }

    }




//    ACTION EVENT METHODS IMPLEMENTATION

    void clearFields() {
        qrImageView.setImage(null);
        qrCodeName.setText(null);
        cashField.clear();
        momoField.clear();
        transactionIdFied.clear();
        displayTotalLabel.setText("0.00");
        makePaymentButton.setDisable(true);
        dateLabel.setText(null);
        payableLabel.setText(null);
        statusLabel.setText(null);
        tollRateLabel.setText(null);
        descriptionBox.clear();
    }

    @FXML
    void cashBoxChecked(ActionEvent event) {
            momoField.setDisable(isCashChecked());
            transactionIdFied.setDisable(isCashChecked());
            momoCheckBox.setDisable(isCashChecked());
            momoField.setText("0.00");
            transactionIdFied.setText("00");
    }

    @FXML void allPaymentCheckBoxClicked() {
        cashCheckBox.setDisable(isAllPaymentChecked());
        momoCheckBox.setDisable(isAllPaymentChecked());
    }

    @FXML
    void chooseQrButtonClicked(ActionEvent event) {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose QR-CODE");
            fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Image Files", "*.png"));
            selectedFile = fileChooser.showOpenDialog(chooseImage.getScene().getWindow());
            fileName = selectedFile.getName();
            filePath = selectedFile.getAbsolutePath();
            Image image = new Image(filePath);
            qrImageView.setImage(image);
            qrCodeName.setText(fileName);
        } catch (NullPointerException e) {
            System.out.println("No file selected.");
            Logger.getAnonymousLogger();
        }
    }

    @FXML
    void momoBoxChecked(ActionEvent event) {
        cashField.setDisable(isMomoChecked());
        cashCheckBox.setDisable(isMomoChecked());
        cashField.setText("0.00");
    }

    @FXML
    void paymentButonClicked(ActionEvent event) {
        alerts = new UserAlerts("SAVE PAYMENT", "ARE YOU SURE YOU WANT TO MAKE PAYMENT FOR SELECTED CUSTOMER?", "YES to save, else CANCEL to abort." );
        try  {
            String checkBoxValue = "";
            double amount = Double.parseDouble(displayTotalLabel.getText());
            long transId = 0;
            int status = 1;

            String qr_token = qrCodeName.getText();
            if (alerts.confirmation()) {
                if (isAllPaymentChecked()) {
                    checkBoxValue = allPaymentCheckBox.getText();
                    transId =  Long.parseLong(transactionIdFied.getText());
                    if (addNewTollLog(qr_token, amount, transId, checkBoxValue, status ) > 0) {
                        refreshTollLogs();
                        clearFields();
                    }
                } else if (isMomoChecked()) {
                    checkBoxValue = momoCheckBox.getText();
                    transId =  Long.parseLong(transactionIdFied.getText());
                    if (addNewTollLog(qr_token, amount, transId, checkBoxValue, status) > 0) {
                        refreshTollLogs();
                        clearFields();
                    }
                } else {
                    if (addNewTollLog(qr_token, amount, transId, checkBoxValue, status ) > 0) {
                        refreshTollLogs();
                        clearFields();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML void clearQrButtonClicked() {
        qrImageView.setImage(null);
        qrCodeName.setText(null);
    }

    @FXML
    void scanQrButtonClicked(ActionEvent event) {
        qrCodeName.setText("This feature is yet to be released in subsequent update.");
    }
    @FXML void validateQrButtonClicked() throws NotFoundException, URISyntaxException, IOException {
        alerts = new UserAlerts("EMPTY FILE", "Please upload a valid QR Code before you validate.");
        if (isQrCodeNameEmpty()) {
            alerts.information();
        } else {
            //block of code for validation...
            qrGenerator = new QRGenerator();
            String values = qrGenerator.readQrImage(filePath);
            descriptionBox.setText(values);

            for (CustomerRegistrationData item : getCustomerRegistration()) {
                if (qrCodeName.getText().equals(item.getQr_code_token())) {
                    dateLabel.setText(item.getValid_date().toString());
                    tollRateLabel.setText(String.valueOf(item.getToll_rate()));
                    payableLabel.setText(String.valueOf(item.getToll_rate()));
                    break;
                }
                LocalDate currentDate = LocalDate.now();
                LocalDate expiryDate = item.getValid_date();
                if (currentDate.isEqual(expiryDate)){
                    statusLabel.setText("QR IS VALID");
                } else if (currentDate.isAfter(expiryDate)) {
                    statusLabel.setText("EXPIRED");
                    statusLabel.setStyle("-fx-background-color: #ffa9a9; -fx-text-fill: red");
                } else {
                    statusLabel.setText("QR IS VALID");
                }
            }
        }
    }

    @FXML void validatePaymentFields() {

        if (isMomoChecked()) {
            makePaymentButton.setDisable(momoField.getText().isBlank() || transactionIdFied.getText().isBlank());
        } else if (isCashChecked()) {
            makePaymentButton.setDisable(cashField.getText().isBlank());
        } else {
            makePaymentButton.setDisable(momoField.getText().isBlank() || transactionIdFied.getText().isBlank() ||cashField.getText().isBlank());
        }
        return;
    }

    @FXML void computePaymentValues() {
        calculatePaymentAmount();
    }

    @FXML void numericValuesForMomo() {

    }

    @FXML void numericValuesOnly(KeyEvent event) {
        if (isMomoChecked()) {
            if (!(event.getCode().isDigitKey() || event.getCode().isArrowKey() || event.getCode().equals(KeyCode.BACK_SPACE) || event.getCode().equals(KeyCode.PERIOD))) {
                momoField.clear();
                transactionIdFied.clear();
            }
        } else if(isCashChecked()) {
            if (!(event.getCode().isDigitKey() || event.getCode().isArrowKey() || event.getCode().equals(KeyCode.BACK_SPACE) || event.getCode().equals(KeyCode.PERIOD))) {
                cashField.clear();
            }
        } else {
            if (!(event.getCode().isDigitKey() || event.getCode().isArrowKey() || event.getCode().equals(KeyCode.BACK_SPACE) || event.getCode().equals(KeyCode.PERIOD))) {
                cashField.clear();
                momoField.clear();
                transactionIdFied.clear();
            }
        }

    }

}//end of class
