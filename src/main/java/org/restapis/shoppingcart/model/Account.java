package org.restapis.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String IBAN;
    private String BIC;
    private String accountHolderName;
    private String bankName;

    private String password;
    private BigDecimal balance;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vault_id", nullable = false)
    private Vault vault;

    public Account(Long id, String iban, String bic, String accountHolderName, String bankName,  BigDecimal balance, String password) {
        this.id =id;
        this.IBAN = iban;
        this.BIC = bic;
        this.accountHolderName = accountHolderName;
        this.bankName = bankName;
        this.balance = balance;
        this.password = password;
    }


}
