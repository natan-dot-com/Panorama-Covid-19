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
 * Classe que no contexto do M.V.C faz o papel de controlador da cena Dashboard do aplicativo.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class DashboardController implements Initializable {

    @FXML
    public WebView whenVaccinated;
    @FXML
    public WebView statsView;
    @FXML
    public WebView newsView;

    private WebEngine vaccineEngine, statsEngine, newsEngine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DashboardSetup.setupWebView(
            whenVaccinated,
            115.0,
            300.0
        );

        DashboardSetup.setupWebView(
            statsView,
            155.0,
            300.0
        );

        DashboardSetup.setupWebView(
            newsView,
            200.0,
            200.0
        );

        vaccineEngine = whenVaccinated.getEngine();
        vaccineEngine.load("http://127.0.0.1:5000/");

        statsEngine = statsView.getEngine();
        statsEngine.load("http://127.0.0.1:5001/");

        newsEngine = newsView.getEngine();
        newsEngine.load("http://127.0.0.1:5002/");
    }
}
