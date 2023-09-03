package com.example.project.service;

import com.example.project.model.FoodItem;

import java.io.IOException;
import java.util.List;

public interface FoodItemService {
    List<FoodItem> getAllItems();
    FoodItem getItemByName(String foodName);
    void saveItem(FoodItem foodItem);
    FoodItem updateItem(FoodItem foodItem);
    byte[] getFoodImage(String fileName) throws IOException;
    String getFoodItemDescription(String itemName);
}
