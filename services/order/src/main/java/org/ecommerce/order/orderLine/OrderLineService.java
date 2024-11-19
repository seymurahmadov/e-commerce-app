package org.ecommerce.order.orderLine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return repository.save(order).getId();
    }
}
