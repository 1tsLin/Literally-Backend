package com.literally.backend.services;

import com.literally.backend.dtos.ProductDTO;
import com.literally.backend.dtos.UserDTO;
import com.literally.backend.entities.Product;
import com.literally.backend.entities.User;
import com.literally.backend.mappers.ProductMapper;
import com.literally.backend.mappers.UserMapper;
import com.literally.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final ProductService productService;

    /*------------------------------------------------------------------------------------------------------------------
                                                    User CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public UserDTO getById(UUID userId) {
        return userMapper.mapToDto(getUserById(userId));
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found : " + userId));
    }

    @Transactional
    public UserDTO create(UserDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("User create dto is null");

        if (userRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("Email already exists : " + dto.getEmail());

        User user = userMapper.mapToEntity(dto);

        userRepository.save(user);

        return userMapper.mapToDto(user);
    }

    @Transactional
    public UserDTO update(UUID userId, UserDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("User update dto is null");

        if (!userRepository.existsById(userId))
            throw new RuntimeException("User not found : " + userId);

        User user = userMapper.mapToEntity(dto);
        user.setId(userId);

        userRepository.save(user);

        return userMapper.mapToDto(user);
    }

    @Transactional
    public void delete(UUID userId) {
        if (!userRepository.existsById(userId))
            throw new RuntimeException("User not found : " + userId);

        userRepository.deleteById(userId);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                              User favorite products operations
    ------------------------------------------------------------------------------------------------------------------*/

    public Set<ProductDTO> getFavorites(UUID userId) {
        User user = getUserById(userId);
        return user.getFavorites().stream().map(productMapper::mapToDto).collect(Collectors.toSet());
    }

    @Transactional
    public void addFavoriteProduct(UUID userId, UUID productId) {
        User user = getUserById(userId);
        Product product = productService.getProductById(productId);

        user.getFavorites().add(product);
        userRepository.save(user);
    }

    @Transactional
    public void removeFavoriteProduct(UUID userId, UUID productId) {
        User user = getUserById(userId);

        user.getFavorites().removeIf(product -> product.getId().equals(productId));
        userRepository.save(user);
    }

}
