package com.panorama;

import java.io.File;
import java.net.URL;

/**
 * Classe para gerenciar a chamada e finalização de scripts no aplicativo.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class ScriptSetup {
    public static ProcessBuilder builder;
    public static Process process, process2;
    public static Process process3;
    public static File app = new File(App.class.getResource("scripts/app.py").getFile());
    public static File news = new File(App.class.getResource("scripts/query_from_db.py").getFile());
    public static File stats = new File(App.class.getResource("scripts/statistics.py").getFile());
    // Obter o caminho absoluto do arquivo.
    public static final String[] scriptNames = {
        "cmd /c " + app.getAbsolutePath(),
        "cmd /c " + news.getAbsolutePath(),
        "cmd /c " + stats.getAbsolutePath()
    };

    public static void runAllLocalServices() {

        try {
            process = Runtime.getRuntime().exec(scriptNames[0]);
            process2 = Runtime.getRuntime().exec(scriptNames[1]);
            process3 = Runtime.getRuntime().exec(scriptNames[2]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
