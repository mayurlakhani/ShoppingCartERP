package org.restapis.shoppingcart.mapper;

import org.restapis.shoppingcart.dto.OrderDto;
import org.restapis.shoppingcart.dto.OrderedItemsDto;
import org.restapis.shoppingcart.model.Order;
import org.restapis.shoppingcart.model.OrderedItems;

public class OrderMapper {

    public static OrderedItemsDto mapToOrderedItemsDto(OrderedItems orderedItems){
            OrderedItemsDto orderedItemsDto1 = new OrderedItemsDto(
                    orderedItems.getId(), orderedItems.getProduct_name(), orderedItems.getSkuCode(), orderedItems.getQuantity(), orderedItems.getPrice()
            );
            return orderedItemsDto1;
    }

    public static OrderedItems mapToOrderedItems(OrderedItemsDto orderedItemsDto){
        OrderedItems orderedItems = new OrderedItems(
                orderedItemsDto.getId(), orderedItemsDto.getProduct_name(), orderedItemsDto.getSkuCode(), orderedItemsDto.getQuantity(), orderedItemsDto.getPrice()
        );
        return orderedItems;
    }
    public static Order mapToOrder(OrderDto orderDto){

        Order order = new Order(orderDto.getId(), orderDto.getOrder_number(),orderDto.getUser(),orderDto.getTotalPrice());
        return order;
    }

    public static OrderDto mapToOrderDto(Order order){
        OrderDto orderDto = new OrderDto(order.getId(), order.getOrder_number(), order.getUser(), order.getTotalPrice());
        return orderDto;
    }
}
