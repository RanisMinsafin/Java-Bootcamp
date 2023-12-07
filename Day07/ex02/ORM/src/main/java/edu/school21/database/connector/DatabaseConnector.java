package edu.school21.database.connector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.database.app.Application;

import javax.sql.DataSource;

public class DatabaseConnector {
    final private static String PROPERTIES_PATH = "src/main/resources/application.properties";
    final private static Application application = new Application(PROPERTIES_PATH);
    private static final String DB_URL = application.getPropertyValue("db.url");
    private static final String DB_USER = application.getPropertyValue("db.login");
    private static final String DB_PASSWORD = application.getPropertyValue("db.password");

    public static DataSource start() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DB_URL);
        hikariConfig.setUsername(DB_USER);
        hikariConfig.setPassword(DB_PASSWORD);
        return new HikariDataSource(hikariConfig);
    }
}