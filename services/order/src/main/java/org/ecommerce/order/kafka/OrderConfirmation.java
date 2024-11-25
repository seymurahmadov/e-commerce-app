package org.ecommerce.order.kafka;

import org.ecommerce.order.customer.CustomerResponse;
import org.ecommerce.order.enumaration.PaymentMethod;
import org.ecommerce.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
