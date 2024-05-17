package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.database.entities.*;
import net.paugallego.housinger.model.database.repositories.*;
import net.paugallego.housinger.model.dto.*;
import net.paugallego.housinger.services.crud.dto.MessageDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReservationDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReviewDTOConverter;
import net.paugallego.housinger.services.crud.entity.PropertyDatesCRUDService;
import net.paugallego.housinger.services.crud.entity.ReservationCRUDService;
import net.paugallego.housinger.services.crud.entity.ReviewCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

    @Autowired
    MessageDTOConverter messageDTOConverter;





    @Value("${spring.default.url2}")
    private String url;

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy, HH:mm:ss");
        return formatter.format(date);
    }

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


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



            simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getId().toString(), "/private", messageDTOConverter.convertFromEntity(message));


            chatRepository.save(message);

            return ResponseEntity.ok().body(dtoRes);




        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/contraOffert")
    public ResponseEntity<?> acceptOffert ( @RequestBody ReservationDTO dto, @RequestParam Long previusId) {
        try {

            System.out.println(previusId);

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


            String mensaje = "A cambio, te propongo tu propiedad ubicada en <br>  "
                    + proposerProperty.getAddress()
                    + "  <br> entre las fechas de: "
                    + formattedDateStart
                    + " - "
                    + formattedDateEnd;

            mensaje += " <br> <a href=" + url + "/seeProposal2?id=" + dtoRes.getId() + "&id2=" + previusId + " class=" + "font-bold text-sky-500>";


            mensaje += "Ver Propuesta " ;

            mensaje += "</a>";

            message.setMessage(mensaje) ;



            simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getId().toString(), "/private", messageDTOConverter.convertFromEntity(message));


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

            UserEntity senderUser = userRepository.findById(senderId).orElse(null);
            UserEntity receiverUser = userRepository.findById(receiverId).orElse(null);


            message.setSender( senderUser.getCustomerEntity());

            message.setReceiver(receiverUser.getCustomerEntity());

            message.setStatus(Status.MESSAGE);

            ReservationEntity reservation = reservationRepository.findById(id).orElse(null);

            String mensaje = "He decidido no aceptar tu propuesta de la propiedad ubicada en: <br> ";

            assert reservation != null;
            mensaje += reservation.getReservationProperty().getAddress();


            message.setMessage(mensaje) ;

            reservationRepository.delete(reservation);

            simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getId().toString(), "/private",  messageDTOConverter.convertFromEntity(message));

            chatRepository.save(message);

            return ResponseEntity.ok().body("ok");


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }

    }


    @GetMapping("/declineOfferTrue/{id}/{id2}/{senderId}/{receiverId}")
    public ResponseEntity<?> sendMessageTrue( @PathVariable Long id,  @PathVariable Long id2, @PathVariable Long senderId, @PathVariable Long receiverId ) {


        try {

            MessageEntity message = new MessageEntity();

            Date today = new Date();

            message.setDate(formatDate(today));

            UserEntity senderUser = userRepository.findById(senderId).orElse(null);
            UserEntity receiverUser = userRepository.findById(receiverId).orElse(null);


            message.setSender( senderUser.getCustomerEntity());

            message.setReceiver(receiverUser.getCustomerEntity());

            message.setStatus(Status.MESSAGE);

            ReservationEntity reservation = reservationRepository.findById(id).orElse(null);

            String mensaje = "He decidido no aceptar tu propuesta de la propiedad ubicada en: <br> ";

            assert reservation != null;
            mensaje += reservation.getReservationProperty().getAddress();


            message.setMessage(mensaje) ;

            reservationRepository.delete(reservation);

            ReservationEntity reservation2 = reservationRepository.findById(id2).orElse(null);

            reservationRepository.delete(reservation2);

            simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getId().toString(), "/private",  messageDTOConverter.convertFromEntity(message));

            chatRepository.save(message);

            return ResponseEntity.ok().body("ok");


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }

    }

    @GetMapping("/acceptOffer/{id}/{id2}/{senderId}/{receiverId}")
    public ResponseEntity<?> confirmar( @PathVariable Long id,  @PathVariable Long id2, @PathVariable Long senderId, @PathVariable Long receiverId ) {


        try {

            MessageEntity message = new MessageEntity();

            Date today = new Date();

            message.setDate(formatDate(today));

            UserEntity senderUser = userRepository.findById(senderId).orElse(null);
            UserEntity receiverUser = userRepository.findById(receiverId).orElse(null);


            message.setSender( senderUser.getCustomerEntity());

            message.setReceiver(receiverUser.getCustomerEntity());

            message.setStatus(Status.MESSAGE);

            ReservationEntity reservation = reservationRepository.findById(id).orElse(null);

            String mensaje = "Perfecto, ¡reserva confirmada!  <br> ";


            message.setMessage(mensaje) ;

            reservation.setType("confirmed");

            reservationRepository.save(reservation);

            ReservationEntity reservation2 = reservationRepository.findById(id2).orElse(null);

            reservation2.setType("confirmed");

            reservationRepository.save(reservation2);

            simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getId().toString(), "/private",  messageDTOConverter.convertFromEntity(message));

            chatRepository.save(message);

            PropertyEntity property1 = reservation.getReservationProperty();
            PropertyEntity property2 = reservation2.getReservationProperty();

            property1.getCalendar().getReservedDates().add(reservation.getDateStart());
            property1.getCalendar().getReservedDates().add(reservation.getDateEnd());

            property2.getCalendar().getReservedDates().add(reservation2.getDateStart());
            property2.getCalendar().getReservedDates().add(reservation2.getDateEnd());

            propertyRepository.save(property1);

            propertyRepository.save(property2);



            return ResponseEntity.ok().body("ok");


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }

    }




    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getByUser (@PathVariable Long id) {
        try {

            UserEntity user = userRepository.findById(id).orElse(null);

            List<ReservationDTO> dtos = dtoConverter.convertFromEntities(reservationRepository.findByReservationUser(user)) ;

            return ResponseEntity.ok().body(dtos);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }

    }

}
