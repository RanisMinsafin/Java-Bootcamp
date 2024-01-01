package edu.school21.sockets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Component("userService")
public class UsersServiceImpl implements UsersService {
    private UsersRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public boolean signIn(User user) {
        Optional<User> optionalUser = repository.findByUsername(user.getUsername());

        if(optionalUser.isPresent()){
            User expected = optionalUser.get();
            return passwordEncoder.matches(user.getPassword(), expected.getPassword());
        }

        return false;
    }

    public Long getUserId(String username){
        Optional<User> user = repository.findByUsername(username);
        return user.orElseGet(User::new).getId();
    }
}