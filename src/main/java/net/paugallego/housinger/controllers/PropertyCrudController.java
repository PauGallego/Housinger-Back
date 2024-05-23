package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.database.entities.BedEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.database.repositories.*;
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

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/property")
public class PropertyCrudController extends AbstractController<PropertyEntity, PropertyDTO, PropertyCRUDService, PropertyDTOConverter, PropertyRepository, Long> {


    @Autowired
    PropertyRepository repo2;

    @Autowired
    BedRepository repo3;

    @Autowired
    ReviewRepository repo4;

    @Autowired
    ReservationRepository repo5;


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
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) {

        try {
            PropertyEntity property = repo2.findById(id).orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));

            List<BedEntity> beds = repo3.findByProperty(property);
            List<ReviewEntity> reviews = repo4.findByReviewProperty(property);
            List<ReservationEntity> reservations = repo5.findByReservationProperty(property);

            Date today = new Date();
            Calendar threeDaysLater = Calendar.getInstance();
            threeDaysLater.setTime(today);
            threeDaysLater.add(Calendar.DAY_OF_MONTH, 3);

            for (ReservationEntity reservation : reservations) {
                if ((reservation.getDateStart().before(threeDaysLater.getTime()) && reservation.getDateEnd().after(today)) || reservation.getDateEnd().before(today)) {
                    return ResponseEntity.badRequest().body("No se puede eliminar la propiedad debido a reservas activas o próximas.");
                }
            }

            for (BedEntity bed : beds) {
                repo3.delete(bed);
            }

            for (ReviewEntity review : reviews) {
                repo4.delete(review);
            }

            for (ReservationEntity reservation : reservations) {
                repo5.delete(reservation);
            }

            repo2.delete(property);



            return ResponseEntity.ok().body("Propiedad eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }



}
