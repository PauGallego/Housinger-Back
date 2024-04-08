package net.paugallego.housinger.services.crud.entity;

import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.RoleEnum;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;
import net.paugallego.housinger.model.dto.RegisterDTO;
import net.paugallego.housinger.services.crud.dto.UserDTOConverter;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.database.repositories.UserRepository;
import net.paugallego.housinger.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserCRUDService extends AbstractCRUDService<UserEntity, UserDTO, UserDTOConverter, UserRepository, Long> implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public UserDetails loadUserById(Long id) {
        return repository.findById(id).get();
    }


    @Autowired
    CustomerRepository customerRepository;

    public UserEntity signUpUser(RegisterDTO dto) {
//
//        if (repository.existsUserByEmail(user.getEmail())) {
//            throw new UserManagementException(BodyErrorCode.EMAIL_ALREADY_EXISTS);
//        } else if(repository.existsUserByUsername(user.getUsername())) {
//            throw new UserManagementException(BodyErrorCode.USERNAME_ALREADY_EXISTS);
//        }
//
//        String activationCode = UUID.randomUUID().toString().substring(0, 8);
//        user.setActivationCode(activationCode);

            UserEntity user = new UserEntity();
            user.setUsername(dto.getUsername());
            user.setEnableAccount(false);
            user.setRoles(Set.of(RoleEnum.U));
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setMail(dto.getMail());

            CustomerEntity customer = new CustomerEntity();

            customer.setPicture(dto.getPicture());
            customer.setSurname(dto.getSurname());
            customer.setName(dto.getName());
            customer.setDni(dto.getDni());
            customerRepository.save(customer);

            user.setCustomerEntity(customer);



        return repository.save(user);


    }
}
