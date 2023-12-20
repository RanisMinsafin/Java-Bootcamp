package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.UUID;

@Component("userService")
public class UsersServiceImpl implements UsersService {
    private Long id = 0L;
    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplate")
                            UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    private String getRandomPassword() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String signUp(String email) {
        if (usersRepository.findByEmail(email).isEmpty()) {
            String password = getRandomPassword();
            usersRepository.save(new User(id++, email, password));
            return password;
        } else {
            return "User with this email already exists";
        }
    }
}
