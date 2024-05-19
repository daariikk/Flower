package ru.shkarupa.backend.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shkarupa.backend.model.Flower;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullOrder {
    private List<OrderItem> flowers;
    private double resultPrice;

    public static FullOrder createFullOrder(List<Flower> flowers) {
        double resultPrice = 0;
        for (Flower flower : flowers)
            resultPrice += flower.getPrice();
        return FullOrder.builder()
                .flowers(OrderItem.createListOfFlowers(flowers))
                .resultPrice(resultPrice)
                .build();
    }
}