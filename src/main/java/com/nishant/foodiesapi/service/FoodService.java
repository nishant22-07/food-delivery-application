package com.nishant.foodiesapi.service;

import com.nishant.foodiesapi.IO.FoodRequest;
import com.nishant.foodiesapi.IO.FoodResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {
    String uploadFile(MultipartFile file);


    FoodResponse addFood(FoodRequest request , MultipartFile file);

    List<FoodResponse> readFoods();

    FoodResponse readFoodById(String id);

    boolean deleteFile(String filename);

    void deleteFood(String id);
}
