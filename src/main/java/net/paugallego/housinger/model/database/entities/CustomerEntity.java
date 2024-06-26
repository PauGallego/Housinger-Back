package net.paugallego.housinger.model.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    @Column(nullable = false, unique = true)
    private String dni;

    private String picture;


    @OneToOne(mappedBy = "customerEntity")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private UserEntity userEntity;
}
