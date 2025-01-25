package com.academy.dream_shop.services.product.image;

import com.academy.dream_shop.exceptions.ProductNotFoundException;
import com.academy.dream_shop.exceptions.ResourceNotFound;
import com.academy.dream_shop.models.Image;
import com.academy.dream_shop.models.Product;
import com.academy.dream_shop.repository.ImageRepository;
import com.academy.dream_shop.repository.ProductRepository;
import com.academy.dream_shop.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ImageService implements IImageService {


    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Image not found with ID: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        if (imageRepository.existsById(id)){
            imageRepository.deleteById(id);
        } else {
            throw new ResourceNotFound("Image not found");
        }
    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
       try {
           Product product = productRepository.findById(productId)
                   .orElseThrow(() -> new ProductNotFoundException("Product not found"));

           Image image = new Image();
           image.setFileName(file.getOriginalFilename());
           image.setFileType(file.getContentType());
           image.setImage(new SerialBlob(file.getBytes()));
           image.setImageUrl("/image/" + file.getOriginalFilename());
           image.setProduct(product);

           return imageRepository.save(image);
       } catch (IOException | SQLException e){
           throw new RuntimeException("Error processing the image file. ");
       }

    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = new Image();
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e){
            throw new RuntimeException(e);
        }

    }
}
