package org.restapis.shoppingcart.repository;

import org.restapis.shoppingcart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from order where user_id =?1 and orer_number=?2", nativeQuery = true)
    Order findByUserIdAndOrderNumber(long userId, String order);
}