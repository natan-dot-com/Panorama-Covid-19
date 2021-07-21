package com.panorama.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.panorama.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class MainpageController implements Initializable {

    public BorderPane mainPane;
    public Button primaryButton;
    public ToggleButton homeButton;
    public ToggleButton dashboardButton;
    public ToggleButton maskButton;
    public ToggleButton aboutUsButton;

    static ToggleButton lastButton = null;
    public Label dashboardLabel;
    public Label maskLabel;
    public Label aboutUsLabel;


    // Uso de polimorfismo aqui para fazer o casting e conseguir usar buttons de múltiplos tipos.
    private void handleButton(MouseEvent e, ToggleButton b, String fxmlFile) throws Exception {
        if (lastButton == b) {
            lastButton.setSelected(true);
            return;
        }

        if (lastButton != null) {
            lastButton.setSelected(false);
        }
        mainPane.setCenter(App.loadFXML(fxmlFile));

        lastButton = b;
    }

    public double getButtonHeight() {
        return dashboardButton.getHeight();
    }

    @FXML
    public void changeToDashboard(MouseEvent event) throws Exception {
        handleButton(event, dashboardButton, "dashboard");
    }

    @FXML
    public void changeToMask(MouseEvent event) throws Exception {
        handleButton(event, maskButton, "masks");
    }

    @FXML
    public void changeToAboutUs(MouseEvent event) throws Exception {
        handleButton(event, aboutUsButton, "aboutUs");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardButton.setFont(App.font);
        dashboardLabel.setFont(App.icons);
        maskButton.setFont(App.font);
        maskLabel.setFont(App.icons);
        aboutUsButton.setFont(App.font);
        aboutUsLabel.setFont(App.icons);
    }
}
