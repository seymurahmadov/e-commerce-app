package org.ecommerce.payment.mapper;

import org.ecommerce.payment.dto.PaymentRequest;
import org.ecommerce.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {

        return Payment.builder()
                .id(request.id())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .amount(request.amount())
                .build();

    }
}
