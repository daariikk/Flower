package ru.shkarupa.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flower")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String description;
    @Column(name = "img_link", nullable = false)
    private String imgLink;
    @ManyToMany(mappedBy = "flowers")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
        user.getFlowers().add(this);

    }

    public void removeUser(User user) {
        user.getFlowers().remove(this);
        users.remove(user);
    }
}
