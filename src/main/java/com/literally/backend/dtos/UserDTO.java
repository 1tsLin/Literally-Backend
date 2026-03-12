package com.literally.backend.dtos;

import com.literally.backend.enums.CurrencyEnum;
import com.literally.backend.enums.LanguageEnum;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
public class UserDTO {
    private UUID id;

    private String firstName;
    private String lastName;
    private String address;

    private LanguageEnum language;
    private CurrencyEnum currency;

    private String email;
    private String password;
    private Boolean isAdmin;

    private Set<ProductDTO> favorites;
    private Set<OrderDTO> orders;
    private Set<CartItemDTO> cart;
    private Set<ReviewDTO> reviews;
}
