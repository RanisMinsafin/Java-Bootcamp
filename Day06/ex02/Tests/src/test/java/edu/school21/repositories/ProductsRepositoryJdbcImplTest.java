package edu.school21.repositories;

import models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import repositories.ProductsRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {
    private static final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "apple", 1L),
            new Product(2L, "banana", 1L),
            new Product(3L, "orange", 2L),
            new Product(4L, "grape", 2L),
            new Product(5L, "watermelon", 5L)
    );
    private static final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(5L, "watermelon", 5L);

    private static final Product EXPECTED_UPDATE_PRODUCT = new Product(5L, "watermelon", 52L);

    private static final Product EXPECTED_SAVE_PRODUCT = new Product(6L, "strawberry", 3L);

    private EmbeddedDatabase dataSource;
    private ProductsRepositoryJdbcImpl repository;

    @BeforeEach
    public void init() throws SQLException {
        this.dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        this.repository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void testFindAll() {
        List<Product> actual = repository.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, actual);
    }

    @Test
    void testFindById() {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(5L).get());
    }

    @Test
    void testUpdate() {
        repository.update(new Product(5L, "watermelon", 52L));
        assertEquals(EXPECTED_UPDATE_PRODUCT, repository.findById(5L).get());
    }

    @Test
    void testSave() {
        repository.save(new Product(6L, "strawberry", 3L));
        assertEquals(EXPECTED_SAVE_PRODUCT, repository.findById(6L).get());
    }

    @Test
    void testDelete() {
        repository.delete(6L);
        assertFalse(repository.findById(6L).isPresent());
    }

    @AfterEach
    void close() {
        dataSource.shutdown();
    }
}
