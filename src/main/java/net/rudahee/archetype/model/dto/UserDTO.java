package net.rudahee.archetype.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String username;
    private String foto;
}
