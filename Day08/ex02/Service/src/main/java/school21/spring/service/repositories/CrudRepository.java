package school21.spring.service.repositories;

import school21.spring.service.models.User;

import java.util.List;
import java.util.Optional;

public interface CrudRepository {
    Optional<User> findById(Long id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(Long id);
}
