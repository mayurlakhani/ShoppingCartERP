package org.restapis.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.restapis.shoppingcart.dto.OrderedItemsDto;

import java.util.List;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String order_number;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderedItems> orderedItemsList;
    private float totalPrice;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Order(Long id, String orderNumber, User user, float totalPrice) {
        this.id = id;
        this.order_number = orderNumber;
        this.user = user;
        this.totalPrice = totalPrice;
    }
}
