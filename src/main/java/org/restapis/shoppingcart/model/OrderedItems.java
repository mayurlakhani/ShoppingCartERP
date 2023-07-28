package org.restapis.shoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orderedItems")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderedItems {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ordered_item_id", nullable = false)
    private Long id;

    private String product_name;
    private String SkuCode;
    private String quantity;
}
