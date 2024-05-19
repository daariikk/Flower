package ru.shkarupa.backend.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shkarupa.backend.data.TestData;
import ru.shkarupa.backend.exception.flower.FlowerAlreadyExistException;
import ru.shkarupa.backend.exception.flower.FlowerNotFoundException;
import ru.shkarupa.backend.exception.user.UserNotFoundException;
import ru.shkarupa.backend.model.Flower;
import ru.shkarupa.backend.model.User;
import ru.shkarupa.backend.repository.FlowerRepository;
import ru.shkarupa.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlowerServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    FlowerRepository flowerRepository;
    @InjectMocks
    FlowerService underTest;

    @Test
    @SneakyThrows
    void creatingExistingFlowerShouldThrowException() {
        final Flower flower = TestData.getFlower1();
        when(flowerRepository.existsByName(anyString())).thenReturn(true);
        assertThrows(FlowerAlreadyExistException.class, () -> underTest.createFlower(flower));
    }

    @Test
    @SneakyThrows
    void creatingNewFlowerShouldCreateNewFlower() {
        final Flower flower = TestData.getFlower1();
        when(flowerRepository.save(any(Flower.class))).thenReturn(flower);

        Flower actualFlower = underTest.createFlower(flower);
        assertEquals(flower, actualFlower);
        verify(flowerRepository, times(1)).save(any(Flower.class));
    }

    @Test
    @SneakyThrows
    void changingAbsentFlowerShouldThrowException() {
        final Flower flower = TestData.getFlower1();
        when(flowerRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(FlowerNotFoundException.class, () -> underTest.changeFlower(flower));
    }

    @Test
    @SneakyThrows
    void changingExistingFlowerShouldChangeFlower() {
        final Flower flower = TestData.getFlower1();
        when(flowerRepository.existsById(anyLong())).thenReturn(true);
        flower.setName("Новое имя");
        when(flowerRepository.save(any(Flower.class))).thenReturn(flower);
        Flower actualFlower = underTest.changeFlower(flower);
        assertEquals("Новое имя", actualFlower.getName());
    }

    @Test
    void getAllFlowersShouldReturnEmptyListWhenNoFlower() {
        final List<Flower> flowers = List.of();
        when(flowerRepository.findAll()).thenReturn(flowers);
        final List<Flower> actualFlowers = underTest.getAllFlowers();
        assertEquals(flowers, actualFlowers);
    }

    @Test
    void getAllFlowersShouldReturnFlowerList() {
        final List<Flower> flowers = List.of(TestData.getFlower1(), TestData.getFlower2());
        when(flowerRepository.findAll()).thenReturn(flowers);
        final List<Flower> actualFlowers = underTest.getAllFlowers();
        assertEquals(flowers, actualFlowers);
    }

    @Test
    void deletingAbsentFlowerShouldThrowException() {
        final Long id = TestData.flower1Id();
        when(flowerRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(FlowerNotFoundException.class, () -> underTest.deleteFlowerById(id));
    }

    @Test
    @SneakyThrows
    void deletingExistingFlowerShouldDeleteFlower() {
        final Long id = TestData.flower1Id();
        when(flowerRepository.existsById(anyLong())).thenReturn(true);
        underTest.deleteFlowerById(id);
        verify(flowerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @SneakyThrows
    void getFlowersOfExistingUser() {
        Flower flower1 = TestData.getFlower1();
        Flower flower2 = TestData.getFlower2();
        User user = TestData.getUser1();
        user.setFlowers(List.of(flower1, flower2));

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        List<Flower> expectedFlowers = List.of(flower1, flower2);
        List<Flower> actualFlowers = underTest.getFlowersOfUser(1L);
        assertEquals(expectedFlowers, actualFlowers);
    }

    @Test
    void getFlowersOfAbsentUserShouldThrowException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> underTest.getFlowersOfUser(1L));
    }
}