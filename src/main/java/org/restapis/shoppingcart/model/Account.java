package org.restapis.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    public Account(String iban, String bic, String accountHolderName, String bankName, String password) {

        this.IBAN = iban;
        this.BIC = bic;
        this.accountHolderName = accountHolderName;
        this.bankName = bankName;
        this.password = password;
    }
}
