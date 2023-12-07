package edu.school21.database.app;

import edu.school21.database.loader.PropertiesLoader;

public class Application {
    private final PropertiesLoader propertiesLoader;

    public Application(String propertiesFilePath) {
        propertiesLoader = new PropertiesLoader(propertiesFilePath);
    }

    public String getPropertyValue(String key) {
        return propertiesLoader.get(key);
    }
}
