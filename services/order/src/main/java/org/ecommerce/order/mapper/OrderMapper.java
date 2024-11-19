package org.ecommerce.order.mapper;

import org.ecommerce.order.dto.OrderRequest;
import org.ecommerce.order.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        if (request == null) {
            return null;
        }
        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .totalAmount(request.amount())
                .build();
    }
}

