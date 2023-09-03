package com.example.project.controller;

import com.example.project.model.FoodItem;
import com.example.project.model.response.StandardResponse;
import com.example.project.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    FoodItemService service;

    //fetches all the food items which are currently available
    @GetMapping
    public ResponseEntity<StandardResponse> fetchAllFoodItems() {
        List<FoodItem> foodItems= service.getAllItems();
        StandardResponse stdResponse = createSuccessResponse("FoodItems Retrieved. Total Available Items =  "+foodItems.size());
        stdResponse.setPayload(foodItems);
        return ResponseEntity.ok(stdResponse);
    }


    //it is protected from sql injection, xss using the validations in place on the members of FoodItem class
    @PostMapping
    public ResponseEntity<StandardResponse> addFoodItem(@Valid @RequestBody FoodItem foodItem) {
        service.saveItem(foodItem);
        StandardResponse stdResponse = createSuccessResponse("FoodItem added.");
        FoodItem item=service.getItemByName(foodItem.getName());
        stdResponse.setPayload(item);
        return ResponseEntity.ok(stdResponse);
    }


    //prevented from SQL Injection and XSS as the name entered will be used in TypedQuery
    @GetMapping("/find")
    public ResponseEntity<StandardResponse> getFoodItemByName(@RequestParam("name") String name) {
        FoodItem item = service.getItemByName(name);
        StandardResponse stdResponse = createSuccessResponse("Item found");
        stdResponse.setPayload(item);
        return ResponseEntity.ok(stdResponse);
    }


    //it is protected from sql injection, xss using the validations in place on the members of FoodItem class
    @PutMapping(value = "/update", consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<StandardResponse> updateFoodItem(@Valid @RequestBody FoodItem foodItem) {
        FoodItem item = service.updateItem(foodItem);
        StandardResponse stdResponse = createSuccessResponse("Updated Item");
        stdResponse.setPayload(item);
        return ResponseEntity.ok(stdResponse);
    }


    /*used to find the image of food item
    * TODO : possibly add an external call for the next milestone submission
    * */
    @GetMapping(value  = "/image", produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> getFoodImage(@RequestParam("itemName") String itemName) throws IOException {
        byte[] bytes = service.getFoodImage(itemName);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }


    //used to find description of the food item
    @GetMapping("/description")
    public ResponseEntity<String> getFoodItemDescription(@RequestParam("itemName") String itemName) {
        String description = service.getFoodItemDescription(itemName);
        return ResponseEntity.ok(description);
    }

    public final StandardResponse createFailureResponse(String _msg) {
        return createFailureResponse("500", _msg);
    }


    public final StandardResponse createFailureResponse(String _statusCode, String _msg) {
        StandardResponse stdResponse = new StandardResponse();
        stdResponse.initFailure(_statusCode, _msg);
        return stdResponse;
    }


    public final StandardResponse createSuccessResponse(String _msg) {
        return createSuccessResponse("200", _msg);
    }


    public final StandardResponse createSuccessResponse(String _statusCode, String _msg) {
        StandardResponse stdResponse = new StandardResponse();
        stdResponse.initSuccess(_statusCode, _msg);
        return stdResponse;
    }
}
