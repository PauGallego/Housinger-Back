package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.model.dto.LoginDTO;
import net.paugallego.housinger.model.dto.RegisterDTO;
import net.paugallego.housinger.services.crud.StorageService;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/v1/fileCustomer")
public class FileCustomerController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload/{customerId}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long customerId) {
        try {
            String response = storageService.uploadImageToFileSystemCustomer(file, customerId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        try {
            byte[] fileBytes = storageService.downloadImageFromFileSystemCustomer(fileName);
            if (fileBytes != null) {
                // Obtener la extensión del archivo
                String fileExtension = getFileExtension(fileName);
                // Formar el tipo MIME para la imagen
                String mimeType = "image/" + fileExtension.toLowerCase();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(mimeType));

                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error downloading file: " + e.getMessage());
        }
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    }