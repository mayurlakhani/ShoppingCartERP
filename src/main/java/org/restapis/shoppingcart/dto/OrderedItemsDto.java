package org.restapis.shoppingcart.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderedItemsDto {
    private Long id;
    private String product_name;
    private String SkuCode;
    private String quantity;
    private float price;
}
