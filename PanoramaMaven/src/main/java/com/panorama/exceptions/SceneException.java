package com.panorama.exceptions;

import java.io.IOException;

/**
 * Classe para tratar exceções quando a cena do aplicativo não é carregada devidamente.
 * Geralmente trata-se de abertura de arquivos .fxml.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class SceneException extends IOException {
    public static final String IRREGULAR_FXML =
            "O arquivo .fxml está com caminho irregular e não foi possível localizá-lo.\n" +
                    "Leia a documentação oficial do JavaFX para detalhes!";

    public SceneException() {

    }

    public SceneException(String message) {
        super(message);
    }
}
