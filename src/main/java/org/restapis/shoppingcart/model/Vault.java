package org.restapis.shoppingcart.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Vault extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vault_gen")
    @SequenceGenerator(name = "vault_gen", sequenceName = "vault_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private BigDecimal credit;
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "vault")
    private Account account;

    public Vault(Date createdAt, Date updatedAt, Long id, BigDecimal credit) {
        super(createdAt, updatedAt);
        this.id = id;
        this.credit = credit;
    }



}
