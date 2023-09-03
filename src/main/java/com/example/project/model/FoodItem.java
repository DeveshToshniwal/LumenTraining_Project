package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Entity
@Validated
@XmlRootElement
@Table(name = "food_item", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name= "id")
    private long id;

    @Column(name = "name", unique=true, nullable=false)
    @NotNull
    @Size(min = 1, max = 32, message = "Food name can have only 1-32 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ,()\\-]{1,32}$", message = "Name of the item can contain only alphanumeric characters, spaces, and dashes, up to 32 characters")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @Column(name = "price")
    @NotNull(message = "Price needs to be specified")
    private BigDecimal price;

    @Column(name = "available", columnDefinition="boolean default true")
    @NotNull(message = "Specifying the availability is necessary")
    private Boolean available;

    public FoodItem() {

    }

    public FoodItem(long id, String name, FoodType foodType, BigDecimal price, Boolean available) {
        this.id = id;
        this.name = name;
        this.foodType = foodType;
        this.price = price;
        this.available = available;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    enum FoodType
    {
        BEVERAGE ("beverage"),
        DESERT ("desert"),
        MAINCOURSE("maincourse");

        private String backendName;

        FoodType(String backendName)
        {
            this.backendName=backendName;
        }

        public String getBackendName()
        {
            return this.backendName;
        }

    }
}
