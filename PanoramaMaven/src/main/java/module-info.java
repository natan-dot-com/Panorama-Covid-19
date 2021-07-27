module com.panorama {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.web;
    requires jdk.crypto.cryptoki;

    opens com.panorama to javafx.fxml;
    exports com.panorama;
    exports com.panorama.controllers;
    exports com.panorama.setup;
    exports com.panorama.exceptions;

    opens com.panorama.controllers to javafx.fxml;
    opens com.panorama.scripts to javafx.fxml;
}