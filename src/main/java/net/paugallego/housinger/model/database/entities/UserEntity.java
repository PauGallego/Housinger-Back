package net.paugallego.housinger.model.database.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    @Column(nullable = false)
    private String mail;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String dni;
    @Column(nullable = false)
    private String foto;
    private Boolean admin;
    private Boolean premium;
    private Boolean isEnabled;

}
