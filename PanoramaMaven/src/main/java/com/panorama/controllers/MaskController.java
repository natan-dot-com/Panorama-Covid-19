package com.panorama.controllers;

import com.panorama.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MaskController implements Initializable {
    @FXML
    WebView maskViewer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine engine = maskViewer.getEngine();
        engine.load(App.class.getResource("html/masks.html").toString());
    }
}
