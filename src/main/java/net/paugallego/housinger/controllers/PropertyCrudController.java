package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.database.repositories.ReservationRepository;
import net.paugallego.housinger.model.dto.MessageDTO;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.model.dto.ReservationDTO;
import net.paugallego.housinger.services.crud.dto.PropertyDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReservationDTOConverter;
import net.paugallego.housinger.services.crud.entity.PropertyCRUDService;
import net.paugallego.housinger.services.crud.entity.ReservationCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/property")
public class PropertyCrudController extends AbstractController<PropertyEntity, PropertyDTO, PropertyCRUDService, PropertyDTOConverter, PropertyRepository, Long> {

    @Autowired
    PropertyCRUDService propertyCRUDService;
    @GetMapping("/new/{id}/{address}")
    public ResponseEntity<?> createNewProperty(@PathVariable Long id, @PathVariable String address) {
        try {
           PropertyDTO dto = service.createnewById(id, address);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @DeleteMapping("/trueDelete/{id}")
    public ResponseEntity<?> createNewProperty(@PathVariable Long id) {
        try {
            PropertyDTO dto = service.deleteTrue(id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }


}
