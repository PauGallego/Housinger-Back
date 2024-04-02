package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.ReservationEntity;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;
import net.paugallego.housinger.model.database.repositories.ReservationRepository;
import net.paugallego.housinger.model.dto.CustomerDTO;
import net.paugallego.housinger.model.dto.ReservationDTO;
import net.paugallego.housinger.services.crud.dto.CustomerDTOConverter;
import net.paugallego.housinger.services.crud.dto.ReservationDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class CustomerCRUDService extends AbstractCRUDService<CustomerEntity, CustomerDTO, CustomerDTOConverter, CustomerRepository, Long> {
}
