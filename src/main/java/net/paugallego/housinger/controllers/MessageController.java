package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.dto.MessageDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.entity.MessageCRUDService;
import net.paugallego.housinger.services.crud.entity.PropertyCharacteristicsCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/chat")

public class MessageController {


    @Autowired
    MessageCRUDService service;

    @GetMapping("/getReceived/{id}")
    public ResponseEntity<?> getByRecivedId(@PathVariable Long id) {
        try {
            List<MessageDTO> dtos = service.findByRecieverId(id);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @GetMapping("/getSent/{id}")
    public ResponseEntity<?> getBySendedId(@PathVariable Long id) {
        try {
            List<MessageDTO> dtos = service.findBySenderId(id);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }


    @GetMapping("/getChat/{id}/{id2}")
    public ResponseEntity<?> getChatByIds(@PathVariable Long id, @PathVariable Long id2) {
        try {
            List<MessageDTO> dtos = service.findByIds(id, id2);
            if (dtos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron mensajes.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

}

