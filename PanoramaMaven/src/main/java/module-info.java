module com.panorama {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.web;

    opens com.panorama to javafx.fxml;
    exports com.panorama;
    exports com.panorama.controllers;
    opens com.panorama.controllers to javafx.fxml;
}