package org.ecommerce.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.order.dto.OrderRequest;
import org.ecommerce.order.exception.BusinessException;
import org.ecommerce.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder (@RequestBody @Valid OrderRequest request) throws BusinessException {
        return ResponseEntity.ok(orderService.createOrder(request));

    }
}
