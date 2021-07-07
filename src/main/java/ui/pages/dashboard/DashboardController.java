package ui.pages.dashboard;

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
    WebView newsViewer, mapViewer, statsView;
    private WebEngine engine, mapEngine, statsEngine;

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
        engine = newsViewer.getEngine();
        engine.load("https://outline.com/3YBnUa");
        hideScrollBar(newsViewer);


        mapEngine = mapViewer.getEngine();
        mapEngine.load(getClass().getResource("/html/map.html").toString());
        hideScrollBar(mapViewer);

        statsEngine = statsView.getEngine();
        statsEngine.load(getClass().getResource("/html/stats.html").toString());
        hideScrollBar(statsView);

    }
}
