package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String username;
    private String password;
    private String dni;
    private String foto;
    private Boolean admin;
    private Boolean premium;
    private Boolean isEnabled;
}
