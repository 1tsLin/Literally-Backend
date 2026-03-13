package com.literally.backend.mappers;

import com.literally.backend.dtos.OrderItemDTO;
import com.literally.backend.entities.Order;
import com.literally.backend.entities.OrderItem;
import com.literally.backend.entities.Product;
import com.literally.backend.services.OrderService;
import com.literally.backend.services.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class OrderItemMapper {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderItemDTO mapToDto(OrderItem entity){
        UUID orderId = entity.getOrder() != null ? entity.getOrder().getId() : null;
        UUID productId = entity.getProduct() != null ? entity.getProduct().getId() : null;

        return OrderItemDTO.builder()
                .orderId(orderId)
                .productId(productId)
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .build();
    }

    public OrderItem mapToEntity(OrderItemDTO dto) {
        Order order = dto.getOrderId() != null ? orderService.getById(dto.getOrderId()) : null;
        Product product = dto.getProductId() != null ? productService.getById(dto.getProductId()) : null;

        return OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }
}
