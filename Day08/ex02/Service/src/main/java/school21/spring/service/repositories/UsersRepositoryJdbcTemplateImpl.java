package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryJdbcTemplate")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate template;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource")
                                           DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> users = template.query(
                "select * from service.users where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? Optional.empty() : Optional.ofNullable(users.get(0));
    }

    @Override
    public List<User> findAll() {
        List<User> users = template.query(
                "select * from service.users",
                new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public void save(User user) {
        template.update(
                "insert into service.users(email, password) values (?, ?)",
                user.getEmail(), user.getPassword());
    }

    @Override
    public void update(User user) {
        template.update(
                "update service.users set email = ?, password = ? where id = ?",
                user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public void delete(Long id) {
        template.update(
                "delete from service.users where id = ?",
                id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> users = template.query(
                "select * from service.users where email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? Optional.empty() : Optional.ofNullable(users.get(0));
    }
}
