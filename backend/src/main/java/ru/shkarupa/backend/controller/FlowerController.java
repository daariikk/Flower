package ru.shkarupa.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shkarupa.backend.dto.flower.FlowerAddRequest;
import ru.shkarupa.backend.model.Flower;
import ru.shkarupa.backend.service.FlowerService;
import ru.shkarupa.backend.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/flower-store")
@RequiredArgsConstructor
public class FlowerController {
    private final UserService userService;
    private final FlowerService flowerService;

    private Flower flowerAddRequestToFlower(FlowerAddRequest flowerAddRequest) {
        return Flower.builder()
                .name(flowerAddRequest.getName())
                .price(flowerAddRequest.getPrice())
                .description(flowerAddRequest.getDescription())
                .imgLink(flowerAddRequest.getImgLink())
                .build();
    }

    @PostMapping("/flowers")
    public ResponseEntity<Void> addFlower(
            @RequestBody FlowerAddRequest flowerAddRequest
    ) {
        flowerService.createFlower(flowerAddRequestToFlower(flowerAddRequest));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{userId}/flower/{flowerId}")
    public ResponseEntity<Void> addFlowerToUser(
            @PathVariable("userId") Long userId,
            @PathVariable("flowerId") Long carId
    ) {
        flowerService.addFlowerToUser(userId, carId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/flowers")
    public ResponseEntity<List<Flower>> getAllFlowers() {
        return ResponseEntity.ok(
                flowerService.getAllFlowers()
        );
    }

    @GetMapping("/flowers/order/name")
    public ResponseEntity<List<Flower>> getAllFlowersOrderedByName() {
        return ResponseEntity.ok().body(flowerService.getFlowersOrderedByName());
    }

    @GetMapping("/flowers/order/price")
    public ResponseEntity<List<Flower>> getAllFlowersOrderedByPrice() {
        return ResponseEntity.ok().body(flowerService.getFlowersOrderedByPrice());
    }

    @GetMapping("/users/{userId}/flowers")
    public ResponseEntity<List<Flower>> getFlowersOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(flowerService.getFlowersOfUser(userId));
    }

    @DeleteMapping("/flowers/{flowerId}")
    public ResponseEntity<Void> deleteFlowerById(@PathVariable Long flowerId) {
        flowerService.deleteFlowerById(flowerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}/flower/{flowerId}")
    public ResponseEntity<Void> deleteFlowerOfUser(
            @PathVariable("userId") Long userId,
            @PathVariable("flowerId") Long carId
    ) {
        flowerService.deleteFlowerAtUser(userId, carId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}/flower")
    public ResponseEntity<Void> deleteAllFlowersOfUser(@PathVariable("userId") Long userId) {
        userService.deleteAllFlowersAtUser(userId);
        return ResponseEntity.ok().build();
    }
}