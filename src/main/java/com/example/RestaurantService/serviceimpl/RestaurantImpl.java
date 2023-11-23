package com.example.RestaurantService.serviceimpl;

import com.example.RestaurantService.dto.Response;
import com.example.RestaurantService.model.Restaurant;
import com.example.RestaurantService.repository.RestaurantRepository;
import com.example.RestaurantService.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RestaurantImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Response addRestaurant(Restaurant restaurant) {
        Response response = new Response(false, new ArrayList<>(), null);
        try {
            Optional.ofNullable(restaurant)
                    .map(restaurantRepository::save)
                    .ifPresent(newRestaurant -> {
                        response.setSuccess(true);
                        response.setResponseData(newRestaurant);
                    });
            if (response.getResponseData() == null) {
                response.getErrMessage().add("Restaurant not inserted");
            }
        } catch (Exception e) {
            response.getErrMessage().add("Restaurant not added");
            log.error("Error in addRestaurant {}", e);
        }
        return response;
    }

    @Override
    public Response viewAllRestaurants() {
        try {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            Response response = restaurants.isEmpty() ?
                    new Response(false, List.of(new String[]{"No restaurants"}), null) :
                    new Response(true, List.of(new String[]{}), restaurants);
            return response;
        } catch (Exception e) {
            log.error("Error in viewAllRestaurants {}", e);
            return new Response(false, List.of(new String[]{"An error occurred"}), null);
        }
    }

    @Override
    public Response deleteRestaurantById(int restaurantId) {
        try {
            Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
            if (restaurantOptional.isPresent()) {
                Restaurant restaurant = restaurantOptional.get();
                restaurantRepository.delete(restaurant);
                return new Response(true, List.of(new String[]{}), null);
            } else {
                return new Response(false, List.of(new String[]{"Restaurant not found"}), null);
            }
        } catch (Exception e) {
            log.error("Error in deleteRestaurantById {}", e);
            return new Response(false, List.of(new String[]{"Restaurant not deleted"}), null);
        }
    }

    @Override
    @Transactional
    public Response updateRestaurant(Restaurant restaurant) {
        try {
            Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurant.getRestaurantId());
            if (restaurantOptional.isPresent()) {
                Restaurant updatedRestaurant = restaurantOptional.get();
                restaurantRepository.save(restaurant);
                return new Response(true, List.of(new String[]{}), updatedRestaurant);
            } else {
                return new Response(false, List.of(new String[]{"Restaurant not found"}), null);
            }
        } catch (Exception e) {
            log.error("Error in updateRestaurant {}", e);
            return new Response(false, List.of(new String[]{"Restaurant not updated"}), null);
        }
    }

//    @Override
//    public Response getRestaurantById(int restaurantId) {
//        Response response = new Response(false, new ArrayList<>(), null);
//        try {
//            Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
//            if (restaurant.isPresent()) {
//                Restaurant restaurantData = restaurant.get();
//                response.setSuccess(true);
//                response.setResponseData(restaurantData);
//            } else {
//                response.getErrMessage().add("Restaurant Not Found");
//            }
//        } catch (Exception e) {
//            response.getErrMessage().add("Restaurant Not Found");
//            log.error("Error in getRestaurantById {}", e);
//        }
//        return response;
//    }

    public Optional<Restaurant> getRestaurantById(int restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }



}

