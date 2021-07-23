package com.panorama.setup;

import java.io.IOException;
import java.util.HashMap;

/**
 * Classe para gerenciar a chamada e finalização de scripts no aplicativo.
 * @author Paulo Henrique de Souza Soares
 * @author Natan Henrique Sanches
 * @author Osni Brito
 * @author Álvaro José Lopes
 */

public class ScriptSetup {
    private static Process whenVaccinated, stats, news;
    private static final HashMap<String, Process> scriptLocations =
            new HashMap<String, Process>() {{
                put("location1", whenVaccinated);
                put("location2", stats);
                put("location3", news);
            }};

    private String getOS() {
        return System.getProperty("os.name");
    }

    private void runIndividualScript(String name) throws IOException {
        String OS = getOS();

        if (OS.contains("Windows")) {
            whenVaccinated = Runtime.getRuntime().exec(
                "C:\\Python\\python.exe"
            );
        }
    }

    public static void runAllLocalServices() {

    }
}
