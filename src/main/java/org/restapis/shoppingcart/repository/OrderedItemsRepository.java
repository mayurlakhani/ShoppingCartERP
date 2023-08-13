package org.restapis.shoppingcart.repository;

import org.restapis.shoppingcart.model.Order;
import org.restapis.shoppingcart.model.OrderedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Long> {

    @Query(value = "SELECT * from ordered_items where sku_code= ?1", nativeQuery = true)
    List<OrderedItems> findBySkuCode(String i);
}