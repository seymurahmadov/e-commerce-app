package org.ecommerce.kafka;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.email.EmailService;
import org.ecommerce.kafka.order.OrderConfirmation;
import org.ecommerce.kafka.payment.PaymentConfirmation;
import org.ecommerce.notification.entity.Notification;
import org.ecommerce.notification.enumaration.NotificationType;
import org.ecommerce.notification.repository.NotificationRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

     private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from the payment-topic Topic:: %s",paymentConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

       var customerName = paymentConfirmation.customerFirstName() +  " " + paymentConfirmation.customerLastName();
       emailService.sentPaymentSuccessEmail(
               paymentConfirmation.customerEmail(),
               customerName,
               paymentConfirmation.amount(),
               paymentConfirmation.orderReference()
       );
    }


    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from the payment-topic Topic:: %s",orderConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        var customerName = orderConfirmation.customer().firstname() +  " " + orderConfirmation.customer().lastname();
        emailService.sentOrderConfirmationSuccessEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );    }
}
