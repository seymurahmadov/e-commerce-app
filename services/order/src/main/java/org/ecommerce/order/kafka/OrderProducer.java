package org.ecommerce.order.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;



    public void sendOrderConfirmation(OrderCorfirmation orderCorfirmation){
        log.info("Sending order confirmation");
        Message<OrderCorfirmation> message = MessageBuilder
                .withPayload(orderCorfirmation)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();


        kafkaTemplate.send(message);

    }
}
