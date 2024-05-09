package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.repositories.BedRepository;
import net.paugallego.housinger.model.database.repositories.CalendarRepository;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.dto.BedDTO;
import net.paugallego.housinger.model.dto.CalendarDTO;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.services.crud.dto.BedDTOConverter;
import net.paugallego.housinger.services.crud.dto.CalendarDTOConverter;
import net.paugallego.housinger.services.crud.entity.BedCRUDService;
import net.paugallego.housinger.services.crud.entity.CalendarCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/v1/bed")
public class BedCrudController extends AbstractController<BedEntity, BedDTO, BedCRUDService, BedDTOConverter, BedRepository, Long> {


    @Autowired
    BedDTOConverter converter;

    @Autowired
    BedRepository repository;

    @Autowired
    PropertyRepository propertyRepository;

    @GetMapping("/getByProperty/{id}")
    public ResponseEntity<?> getByProperty(@PathVariable Long id) {
        try {

            List<BedDTO> dtos = converter.convertFromEntities(repository.findByProperty(propertyRepository.findById(id).orElse(null)));

            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

}
