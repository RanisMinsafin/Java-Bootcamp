package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

@Component
public class UsersServiceImplTest {
    private static UsersService usersService;
    private static UsersService usersServiceTemplate;
    private static UsersRepositoryJdbcImpl usersRepositoryJdbc;
    private static UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate;

    @BeforeAll
    public static void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        usersService = context.getBean("userService", UsersService.class);
        usersServiceTemplate = context.getBean("usersServiceTemplate", UsersService.class);
        usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepositoryJdbcImpl.class);
        usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepositoryJdbcTemplateImpl.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"user1@gmail.com"})
    void testSignUpTemplate(String email) {
        usersServiceTemplate.signUp(email);
        Assertions.assertEquals(email, usersRepositoryJdbcTemplate.findById(1L).get().getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {"user2@gmail.com"})
    void testSignUp(String email) {
        usersService.signUp(email);
        Assertions.assertEquals(email, usersRepositoryJdbc.findById(2L).get().getEmail());
    }
}
