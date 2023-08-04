package org.restapis.shoppingcart.repository;

import org.restapis.shoppingcart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}