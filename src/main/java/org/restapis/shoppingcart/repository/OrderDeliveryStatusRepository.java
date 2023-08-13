package org.restapis.shoppingcart.repository;

import org.restapis.shoppingcart.model.Account;
import org.restapis.shoppingcart.model.OrderDeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDeliveryStatusRepository extends JpaRepository<OrderDeliveryStatus, Long> {

    @Query(value = "select * from order_delivery where order_id =?1", nativeQuery = true)
    OrderDeliveryStatus findByOrderId(long orderId);
}
