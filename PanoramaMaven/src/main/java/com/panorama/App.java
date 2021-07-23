package com.panorama;

import com.panorama.exceptions.SceneException;
import com.panorama.setup.MainpageSetup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

/**
 * Classe principal do aplicativo, que possui o nó Parent da aplicação e a função main.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class App extends Application {

    private static Dimension screenSize;
    // Variáveis são públicas pois são compartiladas no aplicativo.
    public static Font font, icons;

    /**
     * Função padrão de inicialização do JavaFX.
     */
    @Override
    public void init() {
        System.setProperty("prism.lcdtext", "false"); // Evitar que as fontes fiquem com serrilhado.

        screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Pega o tamanho da tela do usuário.

        // Carregamento de fontes.
        font = Font.loadFont(
            App.class.getResourceAsStream("fonts/Montserrat-Bold.ttf"), 12
        );
        icons = Font.loadFont(
            App.class.getResourceAsStream("fonts/Icons.ttf"), 17.5
        );
    }

    /**
     * Função padrão de lançamento do aplicativo.
     * @param stage Componente principal para reprodução visual da aplicação.
     * @throws SceneException Exceção específica para quando o .fxml não é carregado corretamente.
     */

    @Override
    public void start(Stage stage) throws SceneException {
        Scene scene = new Scene(loadFXML("mainpage"), screenSize.getWidth() - 100, screenSize.getHeight() - 100, true, SceneAntialiasing.BALANCED);
        MainpageSetup.stageSetup(
            stage,
            "Panorama COVID-19",
            1280,
            720,
                scene
        );
    }

    /**
     * Função global para o carregamento de arquivos .fxml para a reprodução de cenas da aplicação.
     * @param fxml Nome do arquivo .fxml sem sua formação e caminho absoluto.
     * @return Nó Parent carregado com o .fxml pronto para reprodução.
     * @throws SceneException Exceção específica para quando o .fxml não é carregado corretamente.
     */

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