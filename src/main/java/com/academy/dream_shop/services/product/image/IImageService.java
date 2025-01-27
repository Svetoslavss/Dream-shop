package com.academy.dream_shop.services.product.image;

import com.academy.dream_shop.models.Image;
import com.academy.dream_shop.models.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImage(List<MultipartFile> file, Long productId);
    ImageDto updateImage(MultipartFile file, Long imageId);
}
