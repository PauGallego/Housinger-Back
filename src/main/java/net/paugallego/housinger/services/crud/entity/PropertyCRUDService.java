package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.*;
import net.paugallego.housinger.model.database.repositories.*;
import net.paugallego.housinger.model.dto.MessageDTO;
import net.paugallego.housinger.model.dto.PropertyDTO;
import net.paugallego.housinger.model.dto.ReservationDTO;
import net.paugallego.housinger.services.crud.dto.PropertyDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReservationDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PropertyCRUDService extends AbstractCRUDService<PropertyEntity, PropertyDTO, PropertyDTOConverter, PropertyRepository, Long> {

    @Autowired
    PropertyDTOConverter convertidor;

    @Autowired
    UserRepository repo;


    @Autowired
    PropertyRepository repo2;

    @Autowired
    BedRepository repo3;

    @Autowired
    ReviewRepository repo4;

    @Autowired
    ReservationRepository repo5;
    public PropertyDTO createnewById(Long id, String address){

        PropertyEntity property = new PropertyEntity();



        UserEntity user = new UserEntity();

        user = repo.findById(id).orElse(null);

        property.setUser(user);
        property.setAddress(address);

        repo2.save(property);

        return convertidor.convertFromEntity(property);


    }

    public PropertyDTO deleteTrue(Long id) {
        try {
            PropertyEntity property = repo2.findById(id).orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));

            List<BedEntity> beds = repo3.findByProperty(property);
            List<ReviewEntity> reviews = repo4.findByReviewProperty(property);
            List<ReservationEntity> reservations = repo5.findByReservationProperty(property);

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

            return convertidor.convertFromEntity(property);
        } catch (EntityNotFoundException ex) {

            ex.printStackTrace();
            return null;
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }
    }


}
