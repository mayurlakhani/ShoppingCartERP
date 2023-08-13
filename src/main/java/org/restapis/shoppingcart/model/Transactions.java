package org.restapis.shoppingcart.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_gen")
    @SequenceGenerator(name = "transactions_gen", sequenceName = "transactions_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String transactionId;

    private float totalAmount;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_delivery_status_id")
    private OrderDeliveryStatus orderDeliveryStatus;
}
