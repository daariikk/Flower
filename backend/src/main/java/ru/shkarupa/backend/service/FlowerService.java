package ru.shkarupa.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.shkarupa.backend.exception.flower.FlowerAlreadyExistAtUserException;
import ru.shkarupa.backend.exception.flower.FlowerAlreadyExistException;
import ru.shkarupa.backend.exception.flower.FlowerNotFoundException;
import ru.shkarupa.backend.repository.FlowerRepository;
import ru.shkarupa.backend.repository.UserRepository;
import ru.shkarupa.backend.exception.ApiError;
import ru.shkarupa.backend.exception.flower.FlowerNotFoundAtUserException;
import ru.shkarupa.backend.exception.user.UserNotFoundException;
import ru.shkarupa.backend.model.Flower;
import ru.shkarupa.backend.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlowerService {
    private final UserRepository userRepository;
    private final FlowerRepository flowerRepository;

    public Flower createFlower(Flower flower) throws FlowerAlreadyExistException {
        if (flowerRepository.existsByName(flower.getName())) {
            throw new FlowerAlreadyExistException();
        }
        flower.setUsers(new ArrayList<>());
        return flowerRepository.save(flower);
    }

    public Flower changeFlower(Flower flower) throws FlowerNotFoundException {
        if (!flowerRepository.existsById(flower.getId())) {
            throw new FlowerNotFoundException();
        }
        return flowerRepository.save(flower);
    }

    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }

    public void deleteFlowerById(Long id) throws FlowerNotFoundException {
        if (!flowerRepository.existsById(id)) {
            throw new FlowerNotFoundException();
        }
        flowerRepository.deleteById(id);
    }

    public List<Flower> getFlowersOfUser(Long userId) throws FlowerNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get().getFlowers();
    }

    public void addFlowerToUser(Long userId, Long carId) throws ApiError {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Flower> optionalCar = flowerRepository.findById(carId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (optionalCar.isEmpty()) {
            throw new FlowerNotFoundException();
        }
        Flower flower = optionalCar.get();
        User user = optionalUser.get();
        if (user.getFlowers().contains(flower)) {
            throw new FlowerAlreadyExistAtUserException();
        }
        flower.addUser(userRepository.findById(userId).get());
        flowerRepository.save(flower);
    }

    public void deleteFlowerAtUser(Long userId, Long carId) throws ApiError {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Flower> optionalFlower = flowerRepository.findById(carId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (optionalFlower.isEmpty()) {
            throw new FlowerNotFoundException();
        }
        Flower flower = optionalFlower.get();
        User user = optionalUser.get();
        if (!user.getFlowers().contains(flower)) {
            throw new FlowerNotFoundAtUserException();
        }
        flower.removeUser(user);
        flowerRepository.save(flower);
    }

    public List<Flower> getFlowersOrderedByName() {
        return flowerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Flower> getFlowersOrderedByPrice() {
        return flowerRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }
}