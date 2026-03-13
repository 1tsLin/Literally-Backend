package com.literally.backend.services;

import com.literally.backend.dtos.OrderDTO;
import com.literally.backend.entities.Order;
import com.literally.backend.mappers.OrderMapper;
import com.literally.backend.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

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
}
