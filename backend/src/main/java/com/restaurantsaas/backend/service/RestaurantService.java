
package com.restaurantsaas.backend.service;

import com.restaurantsaas.backend.entity.Restaurant;

import java.util.Optional;

public interface RestaurantService {

    Optional<Restaurant> findBySlug(String slug);

    boolean existsBySlug(String slug);
    
    Restaurant create(String name, String slug);
}
