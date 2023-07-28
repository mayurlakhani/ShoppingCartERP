package org.restapis.shoppingcart.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column
    private String password;
    @Column
    private String email;

}
