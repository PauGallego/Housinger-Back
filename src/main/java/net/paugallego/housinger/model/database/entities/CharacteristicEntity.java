package net.paugallego.housinger.model.database.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharacteristicEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String icon;
    private String description;
    @ManyToMany(mappedBy = "characteristics")
    @JsonBackReference
    @JsonIgnoreProperties("characteristics")
    private Set<PropertyEntity> properties;

}

