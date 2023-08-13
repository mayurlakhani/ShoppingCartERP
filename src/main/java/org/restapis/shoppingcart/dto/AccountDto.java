package org.restapis.shoppingcart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.restapis.shoppingcart.model.Vault;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private Long id;
    private String IBAN;
    private String BIC;
    private String accountHolderName;
    private String bankName;
    private BigDecimal balance;
    private String password;
    private Vault vault;


}
