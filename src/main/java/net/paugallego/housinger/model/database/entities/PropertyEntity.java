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
    @Column(nullable = false, unique = true)
    private String address;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TypeEntity type;
    @ManyToMany
    @JoinColumn(name = "characteristics_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Set<CharacteristicEntity> characteristics;
    private String description;
    @ElementCollection
    private List<String> fotos;
    @ElementCollection
    private List<String> normas;
    private String extraInfo;
    @OneToOne
    @JoinColumn(name = "calendar_id", referencedColumnName = "id" , insertable = false, updatable = false)
    private CalendarEntity calendar;
}

