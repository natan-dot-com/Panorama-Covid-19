package com.panorama.setup;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.web.WebView;
import java.util.Set;

/**
 * Classe que possui a função de otimizar a inicialização da cena Dashboard da aplicação.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class DashboardSetup {

    /**
     * Faz a tarefa de esconder a scrollbar nas páginas desejadas.
     *
     * @param viewer Um webview genérico que possua scrollbar.
     */
    private static void hideScrollBar(WebView viewer) {
        viewer.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
            @Override public void onChanged(ListChangeListener.Change<? extends Node> change) {
                Set<Node> deadSeaScrolls = viewer.lookupAll(".scroll-bar");
                for (Node scroll : deadSeaScrolls) {
                    scroll.setVisible(false);
                }
            }
        });
    }

    /**
     * Faz o setup de um componente WebView da aplicação.
     *
     * @param view WebView que contém conteúdo do aplicativo, renderizando HTML.
     * @param prefHeight Altura de preferência do componente WebView.
     * @param prefWidth Largura de preferência do componente WebView.
     */

    public static void setupWebView(WebView view, double prefHeight, double prefWidth) {
        // Configurar dimensões.
        view.setPrefHeight(prefHeight);
        view.setPrefWidth(prefWidth);
        view.setFontSmoothingType(FontSmoothingType.LCD); // Antiserrilhado na fonte.
        view.setVisible(true); // Tornar visível
        view.setContextMenuEnabled(false); // Não permite que o usuário volte para a página anterior.

        // Evitar que ao usuário tentar arrastar elementos entre os elementos da dashboard.
        // Basicamente vai evitar uma experiência inadequada no ambiente de consulta de dados.
        view.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });

        hideScrollBar(view);
    }
}
