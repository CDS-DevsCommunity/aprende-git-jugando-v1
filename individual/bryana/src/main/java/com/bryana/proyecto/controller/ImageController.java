package com.bryana.proyecto.controller;

import com.bryana.proyecto.model.Image;
import com.bryana.proyecto.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    // Subir imagen
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 1. Guardar el archivo en la carpeta 'uploads'
            Path uploadPath = Paths.get(uploadDir);
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            // 2. Guardar metadatos en la base de datos
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setFilePath(filePath.toString());
            imageRepository.save(image);

            return ResponseEntity.ok("Imagen subida: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al subir: " + e.getMessage());
        }
    }

    // Descargar imagen por ID
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id) {
        try {
            Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

            Path filePath = Paths.get(image.getFilePath());
            Resource resource = new UrlResource(filePath.toUri());

            return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + image.getName() + "\"")
                .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar todas las imágenes (opcional)
    @GetMapping
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
