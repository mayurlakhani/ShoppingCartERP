package org.restapis.shoppingcart.dto;


import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;

    public UserDto(Long id, String username,  String password, String email) {
        this.id =id;
        this.username = username;
        this.password = password;
    }
}
