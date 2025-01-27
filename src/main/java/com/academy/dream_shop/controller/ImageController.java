package com.academy.dream_shop.controller;

import com.academy.dream_shop.models.Image;
import com.academy.dream_shop.response.ApiResponse;
import com.academy.dream_shop.services.product.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.academy.dream_shop.models.dto.ImageDto;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId){
        try {
            List<ImageDto> image = imageService.saveImage(files, productId);
            return ResponseEntity.ok(new ApiResponse("Uploaded successfully.", image));
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed.", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getImageById(@PathVariable Long imageId){
        try {
            Image image = imageService.getImageById(imageId);
            return ResponseEntity.ok(new ApiResponse("Success.", image));
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed.", e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateImage(@RequestParam MultipartFile files , @RequestParam Long imageId){
        try {
            ImageDto imageDto = imageService.updateImage(files, imageId);
            return ResponseEntity.ok(new ApiResponse("Updated successfully", imageDto));
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed.", e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteImageById(@PathVariable Long imageId){
        try {
            imageService.deleteImageById(imageId);
            return ResponseEntity.ok(new ApiResponse("Successfully deleted.", null));
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed.", e.getMessage()));
        }
    }
}
