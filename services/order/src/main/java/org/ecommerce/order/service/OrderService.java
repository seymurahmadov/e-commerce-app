package org.ecommerce.order.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.order.customer.CustomerClient;
import org.ecommerce.order.dto.OrderRequest;
import org.ecommerce.order.exception.BusinessException;
import org.ecommerce.order.mapper.OrderMapper;
import org.ecommerce.order.orderLine.OrderLineRequest;
import org.ecommerce.order.orderLine.OrderLineService;
import org.ecommerce.order.product.ProductClient;
import org.ecommerce.order.product.PurchaseRequest;
import org.ecommerce.order.product.PurchaseResponse;
import org.ecommerce.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class  OrderService {

    private final OrderRepository orderRepository;

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderMapper mapper;

    private final OrderLineService orderLineService;

    public Integer createOrder(@Valid OrderRequest request) throws BusinessException {
      var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(() ->
              new BusinessException("Cannot create order:: No customer exist with the provided ID"));


        List<PurchaseResponse> purchaseResponses = this.productClient.purchaseProducts(request.products());

        var order = this.orderRepository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()

            ));

        }

        return null;
    }
}
