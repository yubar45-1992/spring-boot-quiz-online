package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.domain.ImageData;
import com.example.online_quiz2.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageDataService  {
  private ImageRepository imageRepository;

  public ImageData imageData (MultipartFile file){
    imageRepository.save(ImageData.builder()
        .name(file.getOriginalFilename())
        .type(file.getContentType())
        .imageData()
      .build())
  }

}
