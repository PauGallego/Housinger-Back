package net.paugallego.housinger.model.database.entities;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageEntity {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
