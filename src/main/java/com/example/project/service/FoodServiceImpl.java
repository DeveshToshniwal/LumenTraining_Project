package com.example.project.service;

import com.example.project.exceptions.DataNotFoundException;
import com.example.project.exceptions.DuplicateDataException;
import com.example.project.exceptions.InternalServerException;
import com.example.project.model.FoodItem;
import com.example.project.repository.FoodItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodItemService{

    private static final Logger log = LoggerFactory.getLogger(FoodServiceImpl.class);

    @Autowired
    private FoodItemRepository repository;

    @Override
    public List<FoodItem> getAllItems() {
        return repository.findAvailable();
    }

    @Override
    public FoodItem getItemByName(String foodName) {
        log.debug("Searching for the item name {}",foodName);
        FoodItem item = repository.findFoodItemByName(foodName);
        if(item!=null)
            return item;
        else
        {
            log.debug("No food item with name {} found.",foodName);
            throw new DataNotFoundException("No food item with name : "+foodName+" found.");
        }
    }

    @Override
    public void saveItem(FoodItem foodItem) {
        FoodItem item = repository.findFoodItemByName(foodItem.getName());
        if(item == null)
            repository.save(foodItem);
        else
        {
            log.debug("Saving to database failed as food item with name {} already exists",foodItem.getName());
            throw new DuplicateDataException("Food Item with name "+foodItem.getName()+" already exists");
        }
    }

    @Override
    public FoodItem updateItem(FoodItem foodItem) {
        FoodItem item = repository.findFoodItemByName(foodItem.getName());
        if(item != null)
        {
            item.setPrice(foodItem.getPrice());
            item.setAvailable(foodItem.getAvailable());
            item.setFoodType(foodItem.getFoodType());
            repository.saveAndFlush(item);
            return item;
        }
        else
        {
            log.debug("Updating failed as item with name {} could not be found",foodItem.getName());
            throw new DataNotFoundException("No food item with name : "+foodItem.getName()+" found.");
        }
    }

    @Override
    public byte[] getFoodImage(String fileName) throws IOException {
        try {
            //checking for directory traversal
            String cleanPath = StringUtils.cleanPath(fileName);
            if(cleanPath.contains("../") || cleanPath.contains("/"))
                throw new FileNotFoundException("Invalid Food Item Name");
            ClassPathResource imgFile = new ClassPathResource("static/images/" +fileName.replaceAll(" ","").toLowerCase()+".jpeg");
            return StreamUtils.copyToByteArray(imgFile.getInputStream());
        } catch (FileNotFoundException e) {
            throw new DataNotFoundException("Image not found of "+fileName, e);
        } catch (Exception e) {
            throw new InternalServerException("Internal Processing Error has occured");
        }

    }

    @Override
    public String getFoodItemDescription(String itemName) {
        try {
            //checking for directory traversal
            String cleanPath = StringUtils.cleanPath(itemName);
            if (cleanPath.contains("../") || cleanPath.contains("/"))
                throw new FileNotFoundException("Invalid Food Item Name");

            //preventing command injection
            ClassPathResource dataFile = new ClassPathResource("static/descriptions/" + itemName.replaceAll(" ","").toLowerCase() + ".txt");
            ProcessBuilder builder = new ProcessBuilder("cat", dataFile.getFilename());
            builder.directory(new ClassPathResource("static/descriptions").getFile());
            Process process = builder.start();

            return readData(process, dataFile.getFilename());

        } catch (FileNotFoundException e) {
            throw new DataNotFoundException("Description not found for item "+itemName, e);
        } catch (Exception e) {
            throw new InternalServerException("Internal Processing Error has occured");
        }
    }

    //reading the data from of the process operation
    private String readData(Process process, String name) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new InternalServerException("Internal Processing Error has occured");
        } finally {
            if(content.toString().length() > 1) {
                return content.toString();
            }
            return "Data Unavailable";
        }
    }

}
