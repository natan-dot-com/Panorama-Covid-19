package com.panorama.exceptions;

import java.io.IOException;

/**
 * Classe para tratar exceções quando os scripts não são carregados corretamente.
 * Geralmente trata-se de execução de arquivos .py.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class SetupException extends Exception {

    public static final String IRREGULAR_SCRIPT =
            "O script está com caminho irregular e não foi possível localizá-lo.";

    public static final String IRREGULAR_COMPILER =
            "O caminho para o compilador/interpretador está irregular e não foi possível localizá-lo";

    public SetupException() {

    }

    public SetupException(String message) {
        super(message);
    }
}
