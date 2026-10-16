package com.restaurantsaas.backend.service.impl;

import com.restaurantsaas.backend.entity.Restaurant;
import com.restaurantsaas.backend.repository.RestaurantRepository;
import com.restaurantsaas.backend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Optional<Restaurant> findBySlug(String slug) {
        return restaurantRepository.findBySlug(slug);
    }

    @Override
    public boolean existsBySlug(String slug) {
        return restaurantRepository.existsBySlug(slug);
    }
    
    @Override
    public Restaurant create(String name, String slug) {

        if (restaurantRepository.existsBySlug(slug)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un restaurante con ese slug"
            );
        }

        Restaurant restaurant = new Restaurant(
                UUID.randomUUID(),
                name,
                slug,
                OffsetDateTime.now()
        );

        return restaurantRepository.save(restaurant);
    }
}