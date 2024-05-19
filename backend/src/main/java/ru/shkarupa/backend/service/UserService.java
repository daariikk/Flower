package ru.shkarupa.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shkarupa.backend.repository.FlowerRepository;
import ru.shkarupa.backend.repository.UserRepository;
import ru.shkarupa.backend.exception.user.UserIsAlreadyExistException;
import ru.shkarupa.backend.exception.user.UserNotFoundException;
import ru.shkarupa.backend.model.Flower;
import ru.shkarupa.backend.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FlowerRepository flowerRepository;

    public User addUser(User user) throws UserIsAlreadyExistException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserIsAlreadyExistException();
        }
        user.setFlowers(new ArrayList<>());
        return userRepository.save(user);
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public boolean existUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        for (Flower flower : user.get().getFlowers()) {
            flower.removeUser(user.get());
            if (flower.getUsers().isEmpty()) {
                flowerRepository.delete(flower);
            }
        }
        userRepository.deleteById(id);
    }

    public void deleteAllFlowersAtUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        for (Flower flower : user.getFlowers()) {
            flower.getUsers().remove(user);
        }
        user.setFlowers(new ArrayList<>());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}