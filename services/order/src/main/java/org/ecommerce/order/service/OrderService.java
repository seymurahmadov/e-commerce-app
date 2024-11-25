package org.ecommerce.order.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.order.customer.CustomerClient;
import org.ecommerce.order.dto.OrderRequest;
import org.ecommerce.order.dto.OrderResponse;
import org.ecommerce.order.exception.BusinessException;
import org.ecommerce.order.kafka.OrderConfirmation;
import org.ecommerce.order.kafka.OrderProducer;
import org.ecommerce.order.mapper.OrderMapper;
import org.ecommerce.order.orderLine.OrderLineRequest;
import org.ecommerce.order.orderLine.OrderLineService;
import org.ecommerce.order.payment.PaymentClient;
import org.ecommerce.order.payment.PaymentRequest;
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

    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) throws BusinessException {
      var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(() ->
              new BusinessException("Cannot create order:: No customer exist with the provided ID"));


        List<PurchaseResponse> purchaseProducts = this.productClient.purchaseProducts(request.products());

        var order = this.orderRepository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()

            ));

        }

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts

                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(mapper::fromOrder)
                .toList();
    }

    public OrderResponse findById(Integer id) {
        return this.orderRepository.findById(id)
                .map(this.mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}