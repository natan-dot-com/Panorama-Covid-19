import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.SceneAntialiasing ;
import javafx.stage.StageStyle;
import java.awt.Toolkit;
import java.awt.Dimension;

public class App extends Application {
    private static Dimension screenSize;

    @Override
    public void init() {
        System.setProperty("prism.lcdtext", "false"); // Evitar que as fontes fiquem com serrilhado.
        screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Pega o tamanho da tela do usuário.
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Mainpage.fxml"));
        } catch (Exception e) {
            System.exit(0);
        }

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight(), true, SceneAntialiasing.BALANCED);
        stage.setScene(scene);
        // Ao selecionar o programa no S.O o título da janela será correto.
        stage.setTitle("Panorama COVID-19");
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
