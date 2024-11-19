package org.ecommerce.order.orderLine;

import lombok.RequiredArgsConstructor;
import org.ecommerce.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
