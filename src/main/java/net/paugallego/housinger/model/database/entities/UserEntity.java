package net.paugallego.housinger.model.database.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

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
    @NonNull
    private String mail;
    @NonNull
    private String username;
    @NonNull
    private String password;
    private String dni;
    @Nullable
    private String foto;
    private Boolean admin;
    private Boolean premium;
}
