package com.panorama.controllers;

import com.panorama.App;
import com.panorama.setup.DashboardSetup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe que no contexto do M.V.C faz o papel de controlador da cena Mask do aplicativo.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class MaskController implements Initializable {
    @FXML
    WebView maskViewer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DashboardSetup.setupWebView(maskViewer, -1.0, -1.0);
        WebEngine engine = maskViewer.getEngine();
        engine.load(App.class.getResource("html/masks.html").toString());
    }
}
