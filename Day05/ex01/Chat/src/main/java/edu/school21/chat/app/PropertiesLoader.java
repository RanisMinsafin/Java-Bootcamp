package edu.school21.chat.app;

import java.io.File;
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
        try (FileInputStream fileInputStream = new FileInputStream(new File(path))) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key){
        return properties.getProperty(key);
    }
}
