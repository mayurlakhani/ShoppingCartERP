package org.restapis.shoppingcart.mapper;


import org.restapis.shoppingcart.dto.ProductDto;

import org.restapis.shoppingcart.model.Product;

public class ProductMapper {

    public static ProductDto mapToProductDto(Product product){

       ProductDto productDto1 = new ProductDto(
               product.getId(), product.getName(), product.getDescription(), product.getPrice()
       );

       return productDto1;
    }

    public static Product mapToProduct(ProductDto accountDto){

     Product product = new Product(
        accountDto.getId(), accountDto.getName(), accountDto.getDescription(), accountDto.getPrice()
     );
     return product;
    }

}
