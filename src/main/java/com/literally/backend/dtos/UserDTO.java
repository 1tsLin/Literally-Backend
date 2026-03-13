package com.literally.backend.dtos;

import com.literally.backend.entities.composite_keys.CartItemKey;
import com.literally.backend.enums.CurrencyEnum;
import com.literally.backend.enums.LanguageEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

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

    private Set<UUID> favoriteIds;
    private Set<UUID> orderIds;
    private Set<CartItemKey> cartIds;
    private Set<UUID> reviewIds;
}
