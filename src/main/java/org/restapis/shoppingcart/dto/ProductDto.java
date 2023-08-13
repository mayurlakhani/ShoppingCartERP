package org.restapis.shoppingcart.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;

    private String name;
    private String Description;
    private float price;

}
