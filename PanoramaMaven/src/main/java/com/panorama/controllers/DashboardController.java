package com.panorama.controllers;

import com.panorama.App;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class DashboardController implements Initializable {

    @FXML
    public WebView whenVaccinated;
    @FXML
    public WebView statsView;
    @FXML
    public WebView newsView;

    private WebEngine vaccineEngine, statsEngine, newsEngine;

    private void hideScrollBar(WebView v) {
        v.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
            @Override public void onChanged(ListChangeListener.Change<? extends Node> change) {
                Set<Node> deadSeaScrolls = v.lookupAll(".scroll-bar");
                for (Node scroll : deadSeaScrolls) {
                    scroll.setVisible(false);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vaccineEngine = whenVaccinated.getEngine();
        vaccineEngine.load("http://127.0.0.1:5000/");

        statsEngine = statsView.getEngine();
        statsEngine.load("https://www.google.com/");

        newsEngine = newsView.getEngine();
        newsEngine.load(App.class.getResource("html/masks.html").toString());
    }
}
