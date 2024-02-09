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
    @Column(nullable = false, unique = true)
    private String mail;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String dni;
    private String foto;
    @Column(columnDefinition = "boolean default false")
    private Boolean admin;
    @Column(columnDefinition = "boolean default false")
    private Boolean premium;
    @Column(columnDefinition = "boolean default false")
    private Boolean isEnabled;

}
