package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.entities.ReviewEntity;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import net.paugallego.housinger.model.database.repositories.ReservationRepository;
import net.paugallego.housinger.model.database.repositories.ReviewRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.model.dto.ReviewDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.dto.ReviewDTOConverter;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.services.crud.entity.ReviewCRUDService;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/review")
public class ReviewCrudController extends AbstractController<ReviewEntity, ReviewDTO, ReviewCRUDService, ReviewDTOConverter, ReviewRepository, Long> {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/isReviewable/{idProperty}/{idUser}")
    public ResponseEntity<?> reviewable(@PathVariable Long idProperty, @PathVariable Long idUser) {

        try {
            PropertyEntity property = propertyRepository.findById(idProperty).orElse(null);
            UserEntity user = userRepository.findById(idUser).orElse(null);

            if (property == null || user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property or User not found");
            }

            List<ReservationEntity> reservations = reservationRepository.findByReservationPropertyAndReservationUserAndType(property, user, "confirmed").orElse(null);

            if (reservations == null || reservations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reservations found");
            }

            for (ReservationEntity reserv : reservations) {

                return ResponseEntity.status(HttpStatus.OK).body("yes :)");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reservations found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }



}
