package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

@Configuration
public class TestApplicationConfig {
    @Bean
    public EmbeddedDatabase embeddedDatabase() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .build();
    }
    @Bean
    public UsersRepository usersRepositoryJdbc() {
        return new UsersRepositoryJdbcImpl(embeddedDatabase());
    }
    @Bean
    public UsersRepository usersRepositoryJdbcTemplate() {
        return new UsersRepositoryJdbcTemplateImpl(embeddedDatabase());
    }
    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepositoryJdbc());
    }
    @Bean
    public UsersService usersServiceTemplate() {
        return new UsersServiceImpl(usersRepositoryJdbcTemplate());
    }

}
