package org.restapis.shoppingcart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    private String password;

    public AccountDto(String iban, String bic, String accountHolderName, String bankName, String password) {

        this.IBAN = iban;
        this.BIC = bic;
        this.accountHolderName = accountHolderName;
        this.bankName = bankName;
        this.password = password;
    }
}
