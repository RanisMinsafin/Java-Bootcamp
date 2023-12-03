package edu.school21.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmbeddedDataSourceTest {
    private EmbeddedDatabase dataSource;

    @BeforeEach
    public void init() {
        this.dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Test
    public void checkConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection);
        }
    }
    @AfterEach
    void close() {
        dataSource.shutdown();
    }
}