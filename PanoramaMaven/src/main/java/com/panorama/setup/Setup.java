package com.panorama.setup;

/**
 * Classe para tratar exceções quando os scripts não são carregados corretamente.
 * Geralmente trata-se de execução de arquivos .py.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public interface Setup {
    /**
     * Faz inicia o script no devido sistema operacional.
     * @param osName Identificador do sistema operacional.
     * @param absolutePathCompiler Caminho absoluto do sistema para o compilador/interpretador do script.
     * @param absolutePathScript Caminho absoluto do sistema para o script em si.
     */
    public void starter(String osName, String absolutePathCompiler, String absolutePathScript);
}
