package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.MessageEntity;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;
import net.paugallego.housinger.model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private CustomerRepository repository;

    @MessageMapping("/message")
    public void receiveMessage(@Payload MessageEntity message) {
        // Process the message
        simpMessagingTemplate.convertAndSend("/chatroom/public", message);
    }

    @MessageMapping("/private-message")
    public void recMessage(@Payload MessageDTO message) {


        if (message.getReceiverId() != null ) {

            CustomerEntity receiver = repository.findById(message.getReceiverId()).orElse(null);

            if (receiver != null) {
                simpMessagingTemplate.convertAndSendToUser(receiver.getId().toString(), "/private", message);
                System.out.println("Private message sent: " + message);
            } else {
                System.out.println("Receiver not found for message: " + message);
            }
        } else {
            System.out.println("Invalid receiver or receiver ID is null for message: " + message);
        }
    }



}
