package net.paugallego.housinger.services.crud;

import net.paugallego.housinger.model.database.entities.CustomerEntity;
import net.paugallego.housinger.model.database.entities.PropertyEntity;
import net.paugallego.housinger.model.database.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import net.paugallego.housinger.model.database.repositories.CustomerRepository;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private PropertyRepository propertyRepository;


    @Value("${spring.default.image.path}")
    private String FOLDER_PATH;

    public String uploadImageToFileSystemCustomer(MultipartFile file, Long customerId) throws IOException {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            CustomerEntity customer = customerOptional.get();
            String oldFileName = customer.getPicture();

            // Borra la foto antigua si existe
            if (oldFileName != null) {
                File oldFile = new File(FOLDER_PATH + oldFileName);
                if (oldFile.exists()) {
                    if (oldFile.delete()) {
                        System.out.println("Old file deleted successfully: " + oldFileName);
                    } else {
                        System.err.println("Failed to delete old file: " + oldFileName);
                    }
                } else {
                    System.out.println("Old file does not exist: " + oldFileName);
                }
            }

            String originalFileName = file.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            String filePath = FOLDER_PATH + newFileName;

            File directory = new File(FOLDER_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File dest = new File(filePath);
            file.transferTo(dest);

            customer.setPicture(newFileName);
            customerRepository.save(customer);

            return "File uploaded successfully: " + filePath;
        } else {
            throw new IllegalArgumentException("Customer with ID " + customerId + " not found");
        }
    }


    public String uploadImageToFileSystemProperty(List<MultipartFile> files, Long propertyId) throws IOException {
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        if (propertyEntity.isPresent()) {
            List<String> fileNames = new ArrayList<>();

            for (MultipartFile file : files) {
                String originalFileName = file.getOriginalFilename();
                String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
                String filePath = FOLDER_PATH + fileName;

                File directory = new File(FOLDER_PATH);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                File dest = new File(filePath);
                file.transferTo(dest);

                fileNames.add(fileName);
            }

            PropertyEntity property = propertyEntity.get();
            property.setFotos(fileNames);
            propertyRepository.save(property);

            return "Files uploaded successfully";
        } else {
            throw new IllegalArgumentException("Property with ID " + propertyId + " not found");
        }
    }

    public byte[] downloadImageFromFileSystemCustomer(String fileName) throws IOException {
        File file = new File(FOLDER_PATH + fileName);
        if (file.exists()) {
            return Files.readAllBytes(file.toPath());
        }
        return null;
    }

    public void deleteImagesFromFileSystem(List<String> fileNames) {
        for (String fileName : fileNames) {
            File file = new File(FOLDER_PATH + fileName);
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("File deleted successfully: " + fileName);
                } else {
                    System.err.println("Failed to delete file: " + fileName);
                }
            } else {
                System.out.println("File does not exist: " + fileName);
            }
        }
    }
}
