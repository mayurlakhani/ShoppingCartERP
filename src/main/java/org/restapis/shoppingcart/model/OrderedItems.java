package org.restapis.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ordered_items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderedItems {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String product_name;
    private String SkuCode;
    private String quantity;
    private float price;

}
