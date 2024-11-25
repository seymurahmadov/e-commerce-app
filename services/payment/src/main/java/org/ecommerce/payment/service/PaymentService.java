package org.ecommerce.payment.service;

import lombok.RequiredArgsConstructor;
import org.ecommerce.payment.dto.PaymentRequest;
import org.ecommerce.payment.mapper.PaymentMapper;
import org.ecommerce.payment.notification.NotificationProducer;
import org.ecommerce.payment.notification.PaymentNotificationRequest;
import org.ecommerce.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = this.repository.save(this.mapper.toPayment(request));

        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
