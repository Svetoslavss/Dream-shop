package com.academy.dream_shop.services.product.image;

import com.academy.dream_shop.exceptions.ResourceNotFound;
import com.academy.dream_shop.models.Image;
import com.academy.dream_shop.models.Product;
import com.academy.dream_shop.models.dto.ImageDto;
import com.academy.dream_shop.repository.ImageRepository;
import com.academy.dream_shop.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {


    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductService productService;


    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Image not found with ID: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
        } else {
            throw new ResourceNotFound("Image not found");
        }
    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> imgDto = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String imageUrl = "/api/v1/images/image/download/" + image.getId();
                image.setImageUrl(imageUrl);

                Image saveImage = imageRepository.save(image);
                ImageDto imageDto = convertImageToDto(saveImage);
                imgDto.add(imageDto);

            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return imgDto;
    }

    private ImageDto convertImageToDto(Image image){
        return new ImageDto(image.getId(), image.getFileName(), image.getFileType(), image.getImageUrl());
    }

        @Override
        public ImageDto updateImage (MultipartFile file, Long imageId){
            Image image = imageRepository.findById(imageId)
                    .orElseThrow(() -> new ResourceNotFound("Image does not found"));
            try {
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                Image savedImage = imageRepository.save(image);
                return updateDtoImage(savedImage);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private ImageDto updateDtoImage(Image image) {
        return new ImageDto(
                image.getId(),
                image.getFileName(),
                image.getFileType(),
                image.getImageUrl()
        );
        }
    }
