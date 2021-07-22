package com.panorama.setup;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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
