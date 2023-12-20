package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> users = jdbcTemplate.query(
                "select * from service.users where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? Optional.empty() : Optional.ofNullable(users.get(0));
    }

    @Override
    public List<User> findAll() {
        List<User> users = jdbcTemplate.query(
                "select * from service.users",
                new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(
                "insert into service.users(email) values (?)",
                user.getEmail());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "update service.users set email = ? where id = ?",
                user.getEmail(), user.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "delete from service.users where id = ?",
                id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> users = jdbcTemplate.query(
                "select * from service.users where email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? Optional.empty() : Optional.ofNullable(users.get(0));
    }
}
