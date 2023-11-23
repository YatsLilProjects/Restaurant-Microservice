package com.example.RestaurantService.service;

import com.example.RestaurantService.dto.Response;
import com.example.RestaurantService.model.Restaurant;

import java.util.Optional;


public interface RestaurantService {

    Response addRestaurant(Restaurant restaurant);

    Response viewAllRestaurants();

    Response deleteRestaurantById(int restaurantId);

    Response updateRestaurant(Restaurant restaurant);

    Optional<Restaurant> getRestaurantById(int restaurantId);

}



