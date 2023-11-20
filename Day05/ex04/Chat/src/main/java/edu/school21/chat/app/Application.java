package edu.school21.chat.app;

public class Application {
    private final PropertiesLoader propertiesLoader;

    public Application(String propertiesFilePath) {
        propertiesLoader = new PropertiesLoader(propertiesFilePath);
    }

    public String getPropertyValue(String key) {
        return propertiesLoader.get(key);
    }
}
