package com.literally.backend.controllers;

import com.literally.backend.dtos.OrderDTO;
import com.literally.backend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /*------------------------------------------------------------------------------------------------------------------
                                                  Order CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable UUID orderId) {
        return orderService.getById(orderId);
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO dto) {
        return orderService.create(dto);
    }

    @PutMapping("/{orderId}")
    public OrderDTO updateOrder(@PathVariable UUID orderId, @RequestBody OrderDTO dto) {
        return orderService.update(orderId, dto);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable UUID orderId) {
        orderService.delete(orderId);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                                   Order Cancellation
    ------------------------------------------------------------------------------------------------------------------*/

    // TODO : Add Order Cancellation

}
