package org.restapis.shoppingcart.dto;

import lombok.*;

import org.restapis.shoppingcart.model.User;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;
    private String order_number;
    private List<OrderedItemsDto> orderedItemsList;
    private float totalPrice;
    private User user;

    public OrderDto(Long id, String orderNumber, User user, float totalPrice) {
        this.id = id;
        this.order_number = orderNumber;
        this.totalPrice = totalPrice;
        this.user = user;
    }


}
