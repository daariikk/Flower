package ru.shkarupa.backend.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shkarupa.backend.data.TestData;
import ru.shkarupa.backend.exception.user.UserIsAlreadyExistException;
import ru.shkarupa.backend.exception.user.UserNotFoundException;
import ru.shkarupa.backend.model.Flower;
import ru.shkarupa.backend.model.User;
import ru.shkarupa.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService underTest;

    @Test
    @SneakyThrows
    void addExistingUserShouldThrowException() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        final User user = TestData.getUser1();
        assertThrows(UserIsAlreadyExistException.class, () -> underTest.addUser(user));
    }

    @Test
    @SneakyThrows
    void addNewUserShouldReturnUser() {
        final User user = TestData.getUser1();
        when(userRepository.save(any(User.class))).thenReturn(user);

        final User actualUser = underTest.addUser(user);
        final List<Flower> actualFlowers = actualUser.getFlowers();

        assertEquals(user, actualUser);
        assertEquals(List.of(), actualFlowers);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @SneakyThrows
    void deleteAbsentUserShouldThrowException() {
        final Long userId = 1L;

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> underTest.deleteUserById(userId));
        verify(userRepository, times(0)).deleteById(anyLong());
    }

    @Test
    @SneakyThrows
    void deleteExistingUserShouldDeleteUserById() {
        final Long userId = 1L;
        final User user = TestData.getUser1();
        user.setFlowers(new ArrayList<>());

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(anyLong());

        underTest.deleteUserById(userId);
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getAllUsersShouldReturnEmptyListWhenNoUserExists() {
        when(userRepository.findAll()).thenReturn(List.of());

        final List<User> expectedUsers = List.of();
        final List<User> actualUsers = underTest.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void getAllUsersShouldReturnUsers() {
        List<User> users = List.of(TestData.getUser1(), TestData.getUser2());
        when(userRepository.findAll()).thenReturn(users);
        final List<User> actualUsers = underTest.getAllUsers();
        assertEquals(users, actualUsers);
    }

    @Test
    @SneakyThrows
    void findUserByIdAbsentUserShouldThrowException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> underTest.findUserById(1L));
    }

    @Test
    @SneakyThrows
    void findUserByIdShouldReturnUser() {
        final User user = TestData.getUser1();
        final Long userId = TestData.user1Id();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        final User actualUser = underTest.findUserById(userId);
        assertEquals(user, actualUser);
        verify(userRepository, times(1)).findById(anyLong());
    }
}