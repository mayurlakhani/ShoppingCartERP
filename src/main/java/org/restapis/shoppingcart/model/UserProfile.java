package org.restapis.shoppingcart.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "UserProfile")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String descr;

    @Column
    private String number;

  /*  @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;*/

    @OneToOne(
            cascade =  CascadeType.ALL
            )
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private String gender;

    public UserProfile(Long id, String descr, String number, String gender) {
        this.id = id;
        this.descr = descr;
        this.number = number;
        this.gender = gender;
    }
}
