package edu.school21.database.loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private Properties properties;

    public PropertiesLoader(String path) {
        properties = new Properties();
        loadProperties(path);
    }

    private void loadProperties(String path) {
        try (FileInputStream stream = new FileInputStream(path)) {
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
