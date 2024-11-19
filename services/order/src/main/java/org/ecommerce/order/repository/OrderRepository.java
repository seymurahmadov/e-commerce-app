package org.ecommerce.order.repository;

import org.ecommerce.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
