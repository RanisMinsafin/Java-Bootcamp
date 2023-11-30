package edu.school21.repositories.user;

import exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repositories.user.UsersRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

public class UsersServiceImplTest {
    private UsersRepository repository;
    @BeforeEach
    public void init() {
        this.repository = Mockito.mock(UsersRepository.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCorrectAuthenticate() {
        Mockito.when(repository.authenticate("correct", "correct")).thenReturn(true);
        assertTrue(repository.authenticate("correct", "correct"));
        Mockito.verify(repository, times(1)).authenticate("correct", "correct");
    }

    @Test
    void testIncorrectLogin() {
        Mockito.when(repository.authenticate("incorrect", "correct")).
                thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, ()-> repository.authenticate("incorrect", "correct"));
        Mockito.verify(repository, times(1)).authenticate("incorrect", "correct");
    }

    @Test
    void testIncorrectPassword() {
        Mockito.when(repository.authenticate("correct", "incorrect")).
               thenReturn(false);
        assertFalse(repository.authenticate("correct", "incorrect"));
        Mockito.verify(repository, times(1)).authenticate("correct", "incorrect");
    }

}
