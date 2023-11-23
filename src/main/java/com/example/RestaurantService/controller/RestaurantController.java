package com.example.RestaurantService.controller;


import com.example.RestaurantService.dto.Response;
import com.example.RestaurantService.model.Restaurant;
import com.example.RestaurantService.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/restaurant-service/food-delivery")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/addRestaurant")
    public ResponseEntity<Response> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
        Response response = restaurantService.addRestaurant(restaurant);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/viewAllRestaurants")
    public ResponseEntity<Response> viewAllRestaurants() {
        Response response = restaurantService.viewAllRestaurants();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteRestaurant/{restaurantId}")
    public ResponseEntity<Response> deleteRestaurantById(@PathVariable int restaurantId) {
        Response response = restaurantService.deleteRestaurantById(restaurantId);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateRestaurant")
    public ResponseEntity<Response> updateRestaurant(Restaurant restaurant) {
        Response response = restaurantService.updateRestaurant(restaurant);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/viewRestaurantById/{restaurantId}")
    public ResponseEntity<Optional<Restaurant>> findRestaurantById(@PathVariable("restaurantId") int restaurantId) {
        Optional<Restaurant> response = restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


