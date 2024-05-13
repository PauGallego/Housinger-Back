package net.paugallego.housinger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.RoleEnum;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {


    private Long id;
    private String mail;
    private String username;
    private String password;
    private Boolean enableAccount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;
    private String reasonForExpiredAccount;
    private LocalDateTime lastPasswordChange;
    private LocalDateTime nextPasswordChange;
    private Set<RoleEnum> roles;
    private Long customerEntityId;
    private String name;
    private String surname;
    private String picture;
}
