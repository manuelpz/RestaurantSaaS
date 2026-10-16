
package com.restaurantsaas.backend.service.impl;

import com.restaurantsaas.backend.entity.Category;
import com.restaurantsaas.backend.entity.Restaurant;
import com.restaurantsaas.backend.repository.CategoryRepository;
import com.restaurantsaas.backend.repository.RestaurantRepository;
import com.restaurantsaas.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public Category create(
            UUID restaurantId,
            String name,
            Integer displayOrder) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No existe el restaurante"
                ));

        Category category = new Category(
                restaurant,
                name,
                displayOrder
        );

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllByRestaurant(UUID restaurantId) {
        return categoryRepository
                .findAllByRestaurant_IdOrderByDisplayOrderAsc(restaurantId);
    }

    @Override
    public List<Category> findActiveByRestaurant(UUID restaurantId) {
        return categoryRepository
                .findAllByRestaurant_IdAndActiveTrueOrderByDisplayOrderAsc(
                        restaurantId
                );
    }
}
