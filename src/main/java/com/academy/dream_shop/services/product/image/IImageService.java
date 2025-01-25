package com.academy.dream_shop.services.product.image;

import com.academy.dream_shop.models.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

    Image getImageById(Long id);
    void deleteImageById(Long id);
    Image saveImage(MultipartFile file, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
