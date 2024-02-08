package net.paugallego.housinger.model.database.entities;

import jakarta.annotation.Nullable;
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
public class PropertyEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity user;
    @Column(name = "user_id")
    private Long userId;
    private String address;
    private String type;
    @ManyToMany
    @JoinColumn(name = "characteristics_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Set<CharacteristicEntity> characteristics;
    @Column(name = "characteristics_id")
    private Long characteristicsID;
    private String calendar;
    private String description;
    @ElementCollection
    private List<String> fotos;
    @ElementCollection
    private List<String> normas;
    private String extraInfo;
}

