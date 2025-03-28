package com.bryana.proyecto.repository;

import com.bryana.proyecto.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}