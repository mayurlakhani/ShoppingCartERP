package org.restapis.shoppingcart.dto;


import jakarta.persistence.*;
import lombok.*;
import org.restapis.shoppingcart.model.Account;
import org.restapis.shoppingcart.model.User;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class UserProfileDto {

    private Long id;
    private String descr;
    private String number;
    private String gender;

    private User user;

    private Account account;

    public UserProfileDto(Long id, String descr, String number, String gender) {
        this.id = id;
        this.descr = descr;
        this.number = number;
        this.gender = gender;
    }
}
