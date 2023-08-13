package org.restapis.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.restapis.shoppingcart.enumerations.OrderStatus;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "order_delivery")
public class OrderDeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_delivery_status_gen")
    @SequenceGenerator(name = "order_delivery_status_gen", sequenceName = "order_delivery_status_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private OrderStatus Status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;
}
