package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.dto.FindPropertyByDateDTO;
import net.paugallego.housinger.model.dto.FindPropertyByLocationDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.entity.PropertyDatesCRUDService;
import net.paugallego.housinger.services.crud.entity.PropertyLocationCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(path = "/v1/propertyUser")
public class PropertyUserController {


    @Autowired
    PropertyLocationCRUDService service;


    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getByDTO(@PathVariable("userId") Long id) {
        try {

            List<PropertyCharacteristicsDTO> dtos = service.findByUser(id);

            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }


    @GetMapping("/getCustomer/{userId}")
    public ResponseEntity<?> getByDTOCustomer(@PathVariable("userId") Long id) {
        try {

            List<PropertyCharacteristicsDTO> dtos = service.findByCustomer(id);

            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            List<PropertyCharacteristicsDTO> dtos = service.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }



}
