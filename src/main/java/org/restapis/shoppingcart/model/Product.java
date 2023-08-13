package org.restapis.shoppingcart.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name ="product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String Description;
    private float price;

}
