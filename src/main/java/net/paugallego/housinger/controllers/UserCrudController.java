package net.paugallego.housinger.controllers;

import net.paugallego.housinger.exceptions.ApiErrorEnum;
import net.paugallego.housinger.model.database.entities.*;
import net.paugallego.housinger.model.database.repositories.*;
import net.paugallego.housinger.model.dto.PropertyCharacteristicsDTO;
import net.paugallego.housinger.model.dto.UserDTO;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.services.crud.entity.PropertyCRUDService;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/user")
public class UserCrudController extends AbstractController<UserEntity, UserDTO, UserCRUDService, UserDTOConverter, UserRepository, Long> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PropertyCRUDService propertyCRUDService;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TokenMailingRepository tokenMailingRepository;


    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getAdmin(@PathVariable Long id ){
        try {

            UserEntity use = userRepository.findById(id).orElse(null);

            if(use.getRoles().contains(RoleEnum.A)){
                return ResponseEntity.status(HttpStatus.OK).body("yes");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No");
            }


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

     @PutMapping("/truePut")
    public ResponseEntity<?> putTrue(@RequestBody UserDTO dto ){
        try {


            System.out.println("dto:");
            System.out.println(dto);
            UserEntity user = userRepository.findById(dto.getId()).orElse(null);

            CustomerEntity customer = user.getCustomerEntity();

            customer.setName(dto.getName());
            customer.setSurname(dto.getSurname());
            customer.setName(dto.getName());

            if (dto.getDni().equals("") || dto.getDni() == null){
                customer.setDni(customer.getDni());
            }else{
                customer.setDni(dto.getDni());
            }

            customerRepository.save(customer);

            user.setCustomerEntity(customer);

            user.setUsername(dto.getUsername());
            user.setMail(dto.getMail());

            if (dto.getPassword().equals("") || dto.getPassword() == null){
                user.setPassword(user.getPassword());
            }else{
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            }



            user.setEnableAccount(dto.getEnableAccount());
            user.setRoles(dto.getRoles());

            userRepository.save(user);

            System.out.println(user);

            return ResponseEntity.status(HttpStatus.OK).body("donete");


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }

    @DeleteMapping("/trueDelete/{id}")
    public ResponseEntity<?> trueDelete(@PathVariable Long id ){
        try {

            UserEntity user = userRepository.findById(id).orElse(null);

            List<PropertyEntity>  propiedades = propertyRepository.findByUser(user);

            for (PropertyEntity property : propiedades){
                    propertyCRUDService.deleteTrue(property.getId());
            }

            //chat

            List<MessageEntity> mensajesRecibidos = chatRepository.findByReceiver(user.getCustomerEntity());

            List<MessageEntity> mensajesEnviados = chatRepository.findBySender(user.getCustomerEntity());

            for (MessageEntity mensaje : mensajesRecibidos){

                chatRepository.delete(mensaje);

            }

            for (MessageEntity mensaje : mensajesEnviados){

                chatRepository.delete(mensaje);

            }


            //Reviews
            List<ReviewEntity> reviews = reviewRepository.findByReviewUser(user);


            for (ReviewEntity review : reviews){

                reviewRepository.delete(review);

            }

            //Reservas
            List<ReservationEntity> reservationEntityList = reservationRepository.findByReservationUser(user);

            for (ReservationEntity reservation : reservationEntityList){

                reservationRepository.delete(reservation);

            }

            //User
            List<TokenMailingEntity> tokens  = tokenMailingRepository.findByUserEntity(user).orElse(null);

            for (TokenMailingEntity token : tokens){
                tokenMailingRepository.delete(token);
            }

            CustomerEntity customer = user.getCustomerEntity();

            customerRepository.delete(customer);

            userRepository.delete(user);



            return ResponseEntity.status(HttpStatus.OK).body("yes");


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorEnum.INDETERMINATE_ERROR);
        }
    }
}

