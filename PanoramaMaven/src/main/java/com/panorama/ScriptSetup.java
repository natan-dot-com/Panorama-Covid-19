package com.panorama;

import java.io.File;
import java.net.URL;

/**
 * Classe para gerenciar a chamada e finalização de scripts no aplicativo.
 * OBS: USADA NO MODO DESENVOLVIMENTO.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class ScriptSetup {
    public static ProcessBuilder builder;
    public static Process vaccineProcess, newsProcess, statsProcess;
    public static File app = new File(App.class.getResource("scripts/app.py").getFile());
    public static File news = new File(App.class.getResource("scripts/query_from_db.py").getFile());
    public static File stats = new File(App.class.getResource("scripts/statistics.py").getFile());
    // Obter o caminho absoluto do arquivo.
    public static final String[] scriptNames = {
        "py " + app.getAbsolutePath(),
        "py " + news.getAbsolutePath(),
        "py " + stats.getAbsolutePath()
    };

    /**
     * Função para rodar os scripts [MODO DESENVOLVIMENTO]
     */

    public static void runAllLocalServices() {
        try {
            vaccineProcess = Runtime.getRuntime().exec(scriptNames[0]);
            newsProcess = Runtime.getRuntime().exec(scriptNames[1]);
            statsProcess = Runtime.getRuntime().exec(scriptNames[2]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
