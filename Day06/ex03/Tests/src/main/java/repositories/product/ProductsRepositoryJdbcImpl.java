package repositories.product;

import models.Product;
import repositories.product.ProductsRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private static final String FIND_ALL = "select * from shop.product";
    private static final String FIND_BY_ID = "select * from shop.product where id = ?";
    private static final String UPDATE_PRODUCT = "update shop.product set name = ?, price = ? where id = ?";
    private static final String SAVE_PRODUCT = "insert into shop.product(id, name, price) values(?, ?, ?)";
    private static final String DELETE_PRODUCT = "delete from shop.product where id = ?";

    private Connection connection;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        connection = dataSource.getConnection();
    }

    @Override
    public List<Product> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            List<Product> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("price"));
                products.add(product);
            }
            return products;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            Product product = new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("price"));
            return Optional.of(product);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void update(Product product) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setLong(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void save(Product product) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_PRODUCT)) {
            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setLong(3, product.getPrice());
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}

