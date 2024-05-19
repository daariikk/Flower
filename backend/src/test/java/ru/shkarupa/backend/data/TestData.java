package ru.shkarupa.backend.data;

import ru.shkarupa.backend.model.Flower;
import ru.shkarupa.backend.model.User;

public final class TestData {
    private TestData() {

    }

    public static Long user1Id() {
        return 13L;
    }

    public static Long flower1Id() {
        return 1L;
    }

    public static User getUser1() {
        return User.builder()
                .id(13L)
                .email("test32@gmail.com")
                .firstName("Mark")
                .lastName("Solonchev")
                .password("123")
                .build();
    }

    public static User getUser2() {
        return User.builder()
                .id(19L)
                .email("test52@gmail.com")
                .firstName("Artem")
                .lastName("Loving")
                .password("321")
                .build();
    }

    public static Flower getFlower1() {
        return Flower.builder()
                .id(1L)
                .name("Пурпурные ландыши")
                .price(1700.0)
                .description("Красивый букет")
                .imgLink("test_link")
                .build();
    }

    public static Flower getFlower2() {
        return Flower.builder()
                .id(2L)
                .name("Белые розы")
                .price(6800.0)
                .description("Легендарный цветы")
                .imgLink("another_test_link")
                .build();
    }
}