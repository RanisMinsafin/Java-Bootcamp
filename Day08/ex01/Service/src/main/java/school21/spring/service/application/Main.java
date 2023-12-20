package school21.spring.service.application;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        System.out.println("__________________________________JDBC API__________________________________");
        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println("Find user with id = 1: " + usersRepositoryJdbc.findById(1L).get());
        System.out.println("Find all users: " + usersRepositoryJdbc.findAll());

        usersRepositoryJdbc.delete(2L);
        if(usersRepositoryJdbc.findById(2L).isEmpty()){
            System.out.println("User with id 2 has been deleted successfully");
        }

        usersRepositoryJdbc.save(new User(7L, "user7@example.com"));
        System.out.println("Has been saved user with id = 7: "+ usersRepositoryJdbc.findById(7L).get());

        usersRepositoryJdbc.update(new User(1L, "user1@gmail.com"));
        System.out.println("After update user with id = 1: "+ usersRepositoryJdbc.findById(1L).get());

        System.out.println("Find by email user with id = 4: " +usersRepositoryJdbc.findByEmail("user4@example.com").get());
        System.out.println();

        System.out.println("________________________________JDBC Template________________________________");
        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println("Find user with id = 1: " + usersRepositoryJdbcTemplate.findById(1L).get());
        System.out.println("Find all users: " + usersRepositoryJdbcTemplate.findAll());

        usersRepositoryJdbcTemplate.delete(3L);
        if(usersRepositoryJdbcTemplate.findById(3L).isEmpty()){
            System.out.println("User with id 3 has been deleted successfully");
        }

        usersRepositoryJdbcTemplate.save(new User(8L, "user8@example.com"));
        System.out.println("Has been saved user with id = 8: "+ usersRepositoryJdbcTemplate.findById(8L).get());

        usersRepositoryJdbcTemplate.update(new User(1L, "user1@bk.ru"));
        System.out.println("After update user with id = 1: "+ usersRepositoryJdbcTemplate.findById(1L).get());
        System.out.println("Find by email user with id = 4: " +usersRepositoryJdbcTemplate.findByEmail("user4@example.com").get());
        System.out.println();
    }
}
