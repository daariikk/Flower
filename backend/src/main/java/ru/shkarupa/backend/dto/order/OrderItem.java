package ru.shkarupa.backend.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shkarupa.backend.model.Flower;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    private String name;
    private double price;

    public static List<OrderItem> createListOfFlowers(List<Flower> flowers) {
        List<OrderItem> result = new ArrayList<>();
        flowers.forEach(flower -> result.add(OrderItem.builder()
                .name(flower.getName())
                .price(flower.getPrice())
                .build()));
        return result;
    }
}