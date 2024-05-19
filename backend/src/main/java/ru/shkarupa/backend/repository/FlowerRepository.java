package ru.shkarupa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shkarupa.backend.model.Flower;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    boolean existsByName(String name);
}
