package com.panorama;

import com.panorama.exceptions.SceneException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Dimension screenSize;
    private static Scene scene;
    public static Font font, icons;

    @Override
    public void init() {
        System.setProperty("prism.lcdtext", "false"); // Evitar que as fontes fiquem com serrilhado.
        screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Pega o tamanho da tela do usuário.
        font = Font.loadFont(
            App.class.getResourceAsStream("fonts/Montserrat-Bold.ttf"), 12
        );

        icons = Font.loadFont(
            App.class.getResourceAsStream("fonts/Icons.ttf"), 17.5
        );
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainpage"), screenSize.getWidth()-100, screenSize.getHeight()-100, true, SceneAntialiasing.BALANCED);
        stage.setTitle("Panorama COVID-19");
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setScene(scene);
        stage.getIcons().add(
            new Image(Objects.requireNonNull(App.class.getResourceAsStream("imgs/app-icon.png")))
        );
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws SceneException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new SceneException(SceneException.IRREGULAR_FXML);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}