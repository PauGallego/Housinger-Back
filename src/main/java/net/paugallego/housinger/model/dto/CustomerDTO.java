package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.UserEntity;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private String surname;
    private String dni;
    private String picture;
    private Long userEntityId;
}
