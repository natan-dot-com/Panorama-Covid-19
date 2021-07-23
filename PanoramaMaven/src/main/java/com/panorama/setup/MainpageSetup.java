package com.panorama.setup;

import com.panorama.App;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;

/**
 * Classe que possui a função de otimizar a inicialização da cena Mainpage da aplicação.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class MainpageSetup {
    private static final int MIN_HEIGHT = 720;
    private static final int MIN_WIDTH = 1280;

    /**
     * Faz a configuração da página principal da aplicação.
     * @param pane Painel da cena que vai abrigar grande parte das features.
     */
    public static void setupMainPane(BorderPane pane) {
        pane.setMinHeight(MIN_HEIGHT);
        pane.setMinWidth(MIN_WIDTH);
    }

    /**
     * Faz a configuração da página principal da aplicação.
     * @param stage É a janela principal da aplicação.
     * @param title É o título da janela, reproduzido pelo sistema operacional.
     * @param height Altura mínima do aplicativo.
     * @param width Largura mínima do aplicativo.
     * @param scene Cena da primeira página do aplicativo.
     */
    public static void stageSetup(Stage stage, String title, double width, double height, Scene scene) {
        stage.setTitle(title);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
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

    /**
     * Faz a configuração da página principal da aplicação.
     * @param box Horizontal box da biblioteca JavaFX, que tem a funcionalidade de uma flexbox horizontal.
     * @param prefHeight Altura de preferência do componente Hbox.
     * @param prefWidth Largura de preferência do componente Hbox.
     */
    public static void setupHBOX(HBox box, double prefHeight, double prefWidth, String style) {
        box.setPrefHeight(prefHeight);
        box.setPrefWidth(prefWidth);
        box.setStyle(style);
    }
}
