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
import org.springframework.beans.factory.annotation.Value;
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
    PropertyRepository propertyRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReservationDTOConverter dtoConverter;

    @Value("${spring.default.url2}")
    private String url;

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy, HH:mm:ss");
        return formatter.format(date);
    }


    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage( @RequestBody ReservationDTO dto) {
        try {

            MessageEntity message = new MessageEntity();

            Date today = new Date();

            message.setDate(formatDate(today));

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


            ReservationEntity reservation = new ReservationEntity();
            reservation.setReservationProperty(proposerProperty);
            reservation.setReservationUser(proproserUser);
            reservation.setDateEnd(dateEnd);
            reservation.setDateStart(dateStart);
            reservation.setType("proposal");


             List<ReservationEntity> reservaciones = reservationRepository.findByReservationPropertyAndReservationUserAndType(proposerProperty, proproserUser, "proposal").orElse(null);

            for (ReservationEntity entity: reservaciones){

                reservationRepository.delete(entity);
            }

            reservationRepository.save(reservation);

             ReservationDTO dtoRes = dtoConverter.convertFromEntity(reservation);


            String mensaje = "Me gustaría intercambiar propiedades, estoy interesado en la propiedad ubicada en: <br>  "
                    + proposerProperty.getAddress()
                    + "  <br> entre las fechas de: "
                    + formattedDateStart
                    + " - "
                    + formattedDateEnd;

            mensaje += " <br> <a   href=" + url + "/seeProposal?id=" + dtoRes.getId() + " " +  " class="  +"font-bold text-sky-500>";


            mensaje += "Ver Propuesta " ;

            mensaje += "</a>";

            message.setMessage(mensaje) ;


            chatRepository.save(message);

            return ResponseEntity.ok().body(dtoRes);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/declineOffer/{id}/{senderId}/{receiverId}")
    public ResponseEntity<?> sendMessage( @PathVariable Long id, @PathVariable Long senderId, @PathVariable Long receiverId ) {


        try {

            MessageEntity message = new MessageEntity();

            Date today = new Date();



            message.setDate(formatDate(today));

            CustomerEntity customerSender = customerRepository.findById(senderId).orElse(null);
            CustomerEntity customerReceiver = customerRepository.findById(receiverId).orElse(null);

            message.setSender( customerSender);

            message.setReceiver(customerReceiver);

            message.setStatus(Status.MESSAGE);

            ReservationEntity reservation = reservationRepository.findById(id).orElse(null);



            String mensaje = "He decidido no aceptar tu propuesta de la propiedad ubicada en: <br> ";
            mensaje += reservation.getReservationProperty().getAddress();


            message.setMessage(mensaje) ;

            reservationRepository.delete(reservation);


            chatRepository.save(message);

            return ResponseEntity.ok().body("ok");


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }

    }


}
