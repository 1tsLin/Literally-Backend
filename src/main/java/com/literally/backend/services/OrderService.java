package com.literally.backend.services;

import com.literally.backend.dtos.CartItemDTO;
import com.literally.backend.dtos.OrderDTO;
import com.literally.backend.entities.Order;
import com.literally.backend.entities.OrderItem;
import com.literally.backend.entities.Product;
import com.literally.backend.enums.OrderStatusEnum;
import com.literally.backend.mappers.OrderMapper;
import com.literally.backend.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    /*------------------------------------------------------------------------------------------------------------------
                                                  Order CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public OrderDTO getById(UUID orderId) {
        return orderMapper.mapToDto(getOrderById(orderId));
    }

    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
    }

    @Transactional
    public OrderDTO create(OrderDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Order create dto is null");

        Order order = orderRepository.save(orderMapper.mapToEntity(dto));

        return orderMapper.mapToDto(order);
    }

    @Transactional
    public OrderDTO update(UUID orderId, OrderDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Order update dto is null");

        if (!orderRepository.existsById(orderId))
            throw new RuntimeException("Order not found : " + orderId);

        Order order = orderMapper.mapToEntity(dto);
        order.setId(orderId);

        orderRepository.save(order);

        return orderMapper.mapToDto(order);
    }

    @Transactional
    public void delete(UUID orderId) {
        if (!orderRepository.existsById(orderId))
            throw new RuntimeException("Order not found : " + orderId);

        orderRepository.deleteById(orderId);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                                   Order Cancellation
    ------------------------------------------------------------------------------------------------------------------*/

    @Transactional
    public OrderDTO cancellation(UUID orderId) {
        Order order = getOrderById(orderId);

        if (!order.getStatus().equals(OrderStatusEnum.PAID))
            throw new RuntimeException("Can't cancel order that has been shipped, delivered or already cancelled");

        order.setStatus(OrderStatusEnum.CANCELLED);
        order.setUpdateDate(Instant.now());

        orderRepository.save(order);

        return orderMapper.mapToDto(order);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                                      Cart checkout
    ------------------------------------------------------------------------------------------------------------------*/

    @Transactional
    public OrderDTO checkout(UUID userId) {
        Set<CartItemDTO> cart = cartService.getCartByUserId(userId);

        if (cart.isEmpty())
            throw new RuntimeException("Can't checkout empty cart : " + userId);

        Order order = Order.builder()
                .id(UUID.randomUUID())
                .user(userService.getUserById(userId))
                .status(OrderStatusEnum.PAID)
                .build();

        Set<OrderItem> items = cart.stream().map(item -> {
            Product product = productService.getProductById(item.getProductId());
            return OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(item.getQuantity())
                    .price(product.getPrice())
                    .build();
        }).collect(Collectors.toSet());
        order.setItems(items);
        orderRepository.save(order);

        /*-- Empty cart after order is made --*/
        cartService.clearCart(cart);

        return orderMapper.mapToDto(order);
    }
}
