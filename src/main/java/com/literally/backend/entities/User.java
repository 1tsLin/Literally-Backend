package com.literally.backend.entities;

import com.literally.backend.enums.CurrencyEnum;
import com.literally.backend.enums.LanguageEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;

    private String lastName;

    private String address;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    private String email;

    private String password;

    private Boolean isAdmin;

    @ManyToMany
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> favorites = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<CartItem> cart = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<>();

}
