package com.panorama.controllers;

import com.panorama.App;
import com.panorama.setup.MainpageSetup;
import com.panorama.exceptions.SceneException;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Classe que no contexto do M.V.C faz o papel de controlador da cena Mainpage do aplicativo.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class MainpageController implements Initializable {

    @FXML
    public BorderPane mainPane;
    @FXML
    public ToggleButton homeButton;
    @FXML
    public ToggleButton dashboardButton;
    @FXML
    public ToggleButton maskButton;
    @FXML
    public ToggleButton aboutUsButton;
    @FXML
    public Label dashboardLabel;
    @FXML
    public Label maskLabel;
    @FXML
    public Label aboutUsLabel;
    @FXML
    public HBox horizontalBox;

    static ToggleButton lastButton = null;

    /**
     * Função que lida com o comportamento do botão que troca e carrega as cenas.
     * @param button Botão do estilo Toggle que possui o comportamento esperado na aplicação.
     * @param fxmlFile Nome do arquivo FXML sem o identificador de formatação para o carregamento da cena.
     */
    private void handleButton(ToggleButton button, String fxmlFile) throws SceneException {
        if (lastButton == button) {
            lastButton.setSelected(true);
            return;
        }

        if (lastButton != null) {
            lastButton.setSelected(false);
        }

        mainPane.setCenter(App.loadFXML(fxmlFile));

        lastButton = button;
    }

    /**
     * Função para trocar a cena do painel principal para o Dashboard.
     * @param event Evento de input do usuário.
     */
    @FXML
    public void changeToDashboard(InputEvent event) {
        if (event instanceof MouseEvent) {
            try {
                handleButton(dashboardButton, "dashboard");
            } catch (SceneException e) {
                System.exit(1);
            }
        }
    }

    /**
     * Função para trocar a cena do painel principal para o Dashboard.
     * @param event Evento de input do usuário.
     */
    @FXML
    public void changeToMask(InputEvent event) {
        if (event instanceof MouseEvent) {
            try {
                handleButton(maskButton, "masks");
            } catch (SceneException e) {
                System.exit(1);
            }
        }
    }

    /**
     * Função para trocar a cena do painel principal para o Sobre Nós.
     * @param event Evento de input do usuário.
     */
    @FXML
    public void changeToAboutUs(InputEvent event) {
        if (event instanceof MouseEvent) {
            try {
                handleButton(aboutUsButton, "aboutUs");
            } catch (SceneException e) {
                System.exit(1);
            }
        }
    }

    /**
     * Função padrão da classe Initializable, que executa essa função antes de qualquer outra.
     * @param url Endereço URL padrão do Java.
     * @param resourceBundle Pacote de recursos padrão do Java.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainpageSetup.setupHBOX(
            horizontalBox,
            140.0,
            1280.0,
            "-fx-background-color: #dee5ea;"
        );

        homeButton.setLayoutX(62.0);
        homeButton.setLayoutY(20.0);
        dashboardButton.setFont(App.font);
        dashboardButton.setText("DASHBOARD");
        dashboardLabel.setFont(App.icons);
        maskButton.setFont(App.font);
        maskLabel.setFont(App.icons);
        aboutUsButton.setFont(App.font);
        aboutUsLabel.setFont(App.icons);
    }
}
