package com.example.online_quiz2.repository;

import com.example.online_quiz2.domain.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageData ,Long> {

  Optional<ImageData> findByName(String name);
}
