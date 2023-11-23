package com.example.RestaurantService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "restaurant_generator")
    @SequenceGenerator(
            name = "restaurant_generator",
            sequenceName = "restaurant_sequence_name",
            allocationSize = 1
    )
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "restaurant_name", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9\\s_'-]{2,}$", message = "Invalid restaurant name, should be 2 to 10 characters and contain only letters")
    private String restaurantName;

    @Column(name = "restaurant_contact_no", nullable = false)
    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be a 10-digit number")
    private String restaurantContactNo;

    @Column(name = "restaurant_location")
    @Pattern(regexp = "^[a-zA-Z0-9\\s_'-]{2,}$"
            , message = "Location must consist of 2 to 10 alphanumeric characters or underscores (a-z, A-Z, 0-9, _,-)")
    private String restaurantLocation;

    @Column(name = "restaurant_image")
    private String restaurantImage;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "restaurant")
    private List<CuisineType> restaurantCuisineType;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "restaurant")
    private List<MenuItem> menuItems;

}

   /* @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Rating> ratings;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurant")
    private List<FoodCart> foodCart;*/

