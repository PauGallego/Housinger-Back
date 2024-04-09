package net.paugallego.housinger.services.crud;

import net.paugallego.housinger.model.database.entities.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {

    @Autowired
    private CustomerRepository customerRepository;

    private final String FOLDER_PATH = "/home/paugallego/Pictures/";

    public String uploadImageToFileSystemCustomer(MultipartFile file, Long customerId) throws IOException {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            String originalFileName = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
            String filePath = FOLDER_PATH + fileName;

            File directory = new File(FOLDER_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File dest = new File(filePath);
            file.transferTo(dest);

            CustomerEntity customer = customerOptional.get();
            customer.setPicture(fileName);
            customerRepository.save(customer);

            return "File uploaded successfully: " + filePath;
        } else {
            throw new IllegalArgumentException("Customer with ID " + customerId + " not found");
        }
    }

    public byte[] downloadImageFromFileSystemCustomer(String fileName) throws IOException {
        File file = new File(FOLDER_PATH + fileName);
        System.out.printf(file.getAbsolutePath());
        if (file.exists()) {
            return Files.readAllBytes(file.toPath());
        }
        return null;
    }
}