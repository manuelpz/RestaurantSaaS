package com.restaurantsaas.backend.service;

import com.restaurantsaas.backend.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category create(UUID restaurantId, String name, Integer displayOrder);

    List<Category> findAllByRestaurant(UUID restaurantId);

    List<Category> findActiveByRestaurant(UUID restaurantId);
}