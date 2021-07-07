package ui.pages.mainpage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainpageController {

    @FXML
    BorderPane mainPane;
    @FXML
    ToggleButton dashboardButton;
    @FXML
    ToggleButton maskButton;
    @FXML
    ToggleButton precautionButton;
    @FXML
    ToggleButton aboutUsButton;
    @FXML
    ToggleButton homeButton;
    @FXML
    Pane defaultPane;

    static ToggleButton lastButton = null;

    // Uso de polimorfismo aqui para fazer o casting e conseguir usar buttons de múltiplos tipos.
    private void handleButton(MouseEvent e, ButtonBase b, String fxmlFile) {
        if (lastButton == b) return;

        if (lastButton != null) {
            lastButton.setSelected(false);
        }
        loadUI(fxmlFile);

        lastButton = (ToggleButton) b;
    }

    @FXML
    private void changeToDashboard(MouseEvent event) {
        handleButton(event, dashboardButton, "Dashboard");
    }

    @FXML
    private void changeToPrecaution(MouseEvent event) {
        handleButton(event, precautionButton, "Precaucoes");
    }

    @FXML
    private void changeToMask(MouseEvent event) {
        handleButton(event, maskButton, "Mascaras");
    }

    @FXML
    private void changeToAboutUs(MouseEvent event) {
        handleButton(event, aboutUsButton, "SobreNos");
    }

    @FXML
    private void changeToHome(MouseEvent event) {
        handleButton(event, homeButton, "Home");
    }

    /**
     * Função que concatena strings de modo a formar o caminho do arquivo FXML
     * desejado e leva para o painel principal a tela desejada.
     * @param ui Nome do arquivo.
     */
    public void loadUI(String ui) {
        Parent root = null;
        try {
            String name = "../../../fxml/" + ui + ".fxml";
            root = FXMLLoader.load(getClass().getResource(name));
        } catch (Exception e) { System.out.println("foi aqui 2"); }

        mainPane.setCenter(root);
    }
}
