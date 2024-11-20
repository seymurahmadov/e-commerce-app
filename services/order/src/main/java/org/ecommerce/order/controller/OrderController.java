package org.ecommerce.order.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ecommerce.order.dto.OrderRequest;
import org.ecommerce.order.dto.OrderResponse;
import org.ecommerce.order.exception.BusinessException;
import org.ecommerce.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder (@RequestBody @Valid OrderRequest request) throws BusinessException {
        return ResponseEntity.ok(orderService.createOrder(request));

    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }


    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findAll(@PathVariable("order-id") Integer orderId){
        return ResponseEntity.ok(orderService.findById(orderId));
    }
}
