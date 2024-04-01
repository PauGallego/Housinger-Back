package net.paugallego.housinger.model.database.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private List<String> message;
    @ElementCollection
    private List<Date> date;
    @ManyToOne
    @JoinColumn(name = "userSend_id", referencedColumnName = "id")
    private UserEntity userSend;
    @ManyToOne
    @JoinColumn(name = "userReceive_id", referencedColumnName = "id")
    private UserEntity userReceive;



}

