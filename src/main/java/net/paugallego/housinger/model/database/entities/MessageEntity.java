package net.paugallego.housinger.model.database.entities;

import jakarta.persistence.*;
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
public class MessageEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    @ElementCollection
    private List<String> message;
    @ManyToOne
    @JoinColumn(name = "userSend_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userSend;
    @Column(name = "userSend_id")
    private Long userSendId;
    @ManyToOne
    @JoinColumn(name = "userReceive_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userReceive;
    @Column(name = "userReceive_id")
    private Long userReceiveId;



}

