package com.example.project.repository;

import com.example.project.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {


    @Query(value = "Select * from food_item f WHERE f.name=?1", nativeQuery = true)
    FoodItem findFoodItemByName(String foodName);

    @Query(value = "Select * from food_item f WHERE f.available=true", nativeQuery = true)
    List<FoodItem> findAvailable();

    @Query(value = "Select f.name from food_item f WHERE f.available=true", nativeQuery = true)
    List<String> findFoodItemNames();

}
