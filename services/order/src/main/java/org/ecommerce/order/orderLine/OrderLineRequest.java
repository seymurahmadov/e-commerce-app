package org.ecommerce.order.orderLine;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        Double quantity
) {
}
