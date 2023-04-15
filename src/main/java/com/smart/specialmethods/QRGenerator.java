package com.smart.specialmethods;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.ImageReader;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;

import java.util.Calendar;
import java.util.HashMap;


public class QRGenerator {
    private String customerName;
    private String registrationNo;
    private String vehicleType;
    private String vehicleClass;
    private double tollRate;
    private ImageView imageView;

    public QRGenerator(String customerName, String registrationNo, String vehicleType, String vehicleClass, double tollRate, ImageView imageView) {
        this.customerName = customerName;
        this.registrationNo = registrationNo;
        this.vehicleType = vehicleType;
        this.vehicleClass = vehicleClass;
        this.tollRate = tollRate;
        this.imageView = imageView;
    }
    public QRGenerator(String customerName, String registrationNo, String vehicleType, double tollRate, String vehicleClass) {
        this.customerName = customerName;
        this.registrationNo = registrationNo;
        this.vehicleType = vehicleType;
        this.tollRate = tollRate;
        this.vehicleClass = vehicleClass;
    }

    public QRGenerator(){}
    public String generateQRCode() throws WriterException, IOException {
        String fileName = customerName + " " + Calendar.getInstance().getTimeInMillis() + ".png";
        String filePath = "C:\\Users\\Druglord\\Documents\\Qr_codes\\" +fileName;
        int size = 400;
        String QrCodeData = "Customer Name: " + customerName + "\n" + "Registration No: " +registrationNo + "\n" + "Vehicle Type: " + vehicleType + "\n" + "Vehicle Class: "+vehicleClass + "\n" + "Toll Rate: " + tollRate;

//        // Configure the QR code encoding options
        HashMap<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        // Generate the QR code image and write it to a file
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(QrCodeData, BarcodeFormat.QR_CODE, size, size, hashMap);
        Path fileDirector = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", fileDirector);


        Image image = new Image(filePath);
        imageView.setImage(image);
        return fileName;
    }


    public String readQrImage(String filePath) throws URISyntaxException, IOException, NotFoundException {
//        BufferedImage reader = ImageReader.readImage(new URI(filePath));
        BufferedImage bufferedImage = ImageIO.read(new File(filePath));
        BufferedImageLuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitMap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Result result = new MultiFormatReader().decode(bitMap);
        return result.getText();
    }



    public double getTollRate() {
        return tollRate;
    }

    public void setTollRate(double tollRate) {
        this.tollRate = tollRate;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }
}
