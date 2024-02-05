package net.paugallego.archetype.model.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExampleEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String example1;
    private Double example2;
    private Boolean example3;
    private String example4;
}
