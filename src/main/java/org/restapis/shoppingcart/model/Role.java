package org.restapis.shoppingcart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.restapis.shoppingcart.enumerations.ERole;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
