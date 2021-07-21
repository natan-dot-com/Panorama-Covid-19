package com.panorama.controllers;

import java.io.IOException;

import com.panorama.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {

    public Button secondaryButton;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("mainpage");
    }
}