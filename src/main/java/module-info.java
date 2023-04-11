module com.smart.smartt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.smart to javafx.fxml;
    exports com.smart;
    exports com.smart.controllers;
    exports com.smart.specialmethods;





    opens com.smart.controllers to javafx.fxml;
}