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
@RequestMapping(path = "/v1/propertyLocation")
public class PropertyLocationController {


    @Autowired
    PropertyLocationCRUDService service;


    @PostMapping("/get")
    public ResponseEntity<?> getByDTO(@RequestBody FindPropertyByLocationDTO dto) {
        try {
            String location = dto.getLocation();

            List<PropertyCharacteristicsDTO> dtos = service.findByLocation(location);


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
