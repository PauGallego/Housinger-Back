package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.database.entities.*;
import net.paugallego.housinger.model.database.repositories.*;
import net.paugallego.housinger.model.dto.CalendarDTO;
import net.paugallego.housinger.model.dto.ReservationDTO;
import net.paugallego.housinger.model.dto.ReservationProposalDTO;
import net.paugallego.housinger.model.dto.ReviewDTO;
import net.paugallego.housinger.services.crud.dto.ReservationDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReviewDTOConverter;
import net.paugallego.housinger.services.crud.entity.PropertyDatesCRUDService;
import net.paugallego.housinger.services.crud.entity.ReservationCRUDService;
import net.paugallego.housinger.services.crud.entity.ReviewCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/reservation")
public class ReservationCrudController extends AbstractController<ReservationEntity, ReservationDTO, ReservationCRUDService, ReservationDTOConverter, ReservationRepository, Long> {


    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PropertyRepository propertyRepository;


    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage( @RequestBody ReservationDTO dto) {
        try {

            MessageEntity message = new MessageEntity();

            Date today = new Date();

            message.setDate(today.toString());

            //User que lo manda
            UserEntity proproserUser = userRepository.findById(dto.getReservationUserId()).orElse(null);

            //Propeidad que quiere
            PropertyEntity proposerProperty = propertyRepository.findById(dto.getReservationPropertyId()).orElse(null);

            assert proproserUser != null;
            message.setSender( proproserUser.getCustomerEntity());

            assert proposerProperty != null;
            message.setReceiver(proposerProperty.getUser().getCustomerEntity());

            message.setStatus(Status.MESSAGE);

            Date dateStart = dto.getDateStart();
            Date dateEnd = dto.getDateEnd();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            String formattedDateStart = dateFormat.format(dateStart);
            String formattedDateEnd = dateFormat.format(dateEnd);
            String mensaje = "Me gustaría intercambiar propiedades, sería la propiedad ubicada en "
                    + proposerProperty.getAddress()
                    + " entre las fechas de: "
                    + formattedDateStart
                    + " - "
                    + formattedDateEnd;

            message.setMessage(mensaje) ;

            chatRepository.save(message);

            return ResponseEntity.ok().body("donete");


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }


}
