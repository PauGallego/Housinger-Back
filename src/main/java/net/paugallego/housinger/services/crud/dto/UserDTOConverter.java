package net.paugallego.housinger.services.crud.dto;

import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.RoleEnum;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserDTOConverter extends AbstractDTOConverter<UserEntity, UserDTO> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserEntity convertFromDTO(UserDTO userDTO) {
        UserEntity entity = new UserEntity();

        entity.setId(userDTO.getId());
        entity.setMail(userDTO.getMail());
        entity.setCreateTime(userDTO.getCreateTime());
        entity.setEnableAccount(userDTO.getEnableAccount());
        entity.setRoles(userDTO.getRoles());
        entity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        entity.setDeleteTime(userDTO.getDeleteTime());
        entity.setLastPasswordChange(userDTO.getLastPasswordChange());
        entity.setNextPasswordChange(userDTO.getNextPasswordChange());
        entity.setReasonForExpiredAccount(userDTO.getReasonForExpiredAccount());
        entity.setUsername(userDTO.getUsername());
        entity.setUpdateTime(userDTO.getUpdateTime());

        CustomerEntity customer = customerRepository.findById(userDTO.getCustomerEntityId()).orElse(null);

        customer.setName(userDTO.getName());
        customer.setSurname(userDTO.getSurname());

        customerRepository.save(customer);

        entity.setCustomerEntity(customer );

        return entity;

    }

    @Override
    public UserDTO convertFromEntity(UserEntity entity) {
        UserDTO dto = new UserDTO();

        dto.setMail(entity.getMail());
        dto.setRoles(entity.getRoles());
        dto.setPassword(entity.getPassword());
        dto.setDeleteTime(entity.getDeleteTime());
        dto.setCustomerEntityId(entity.getCustomerEntity().getId());
        dto.setId(entity.getId());
        dto.setNextPasswordChange(entity.getNextPasswordChange());
        dto.setLastPasswordChange(entity.getLastPasswordChange());
        dto.setEnableAccount(entity.getEnableAccount());
        dto.setCreateTime(entity.getCreateTime());
        dto.setReasonForExpiredAccount(entity.getReasonForExpiredAccount());
        dto.setUsername(entity.getUsername());
        dto.setUpdateTime(entity.getUpdateTime());
        dto.setName(entity.getCustomerEntity().getName());
        dto.setSurname(entity.getCustomerEntity().getSurname());
        dto.setPicture(entity.getCustomerEntity().getPicture());



        return dto;
    }
}
