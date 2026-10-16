
package com.restaurantsaas.backend.repository;

import com.restaurantsaas.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository
        extends JpaRepository<Category, UUID> {

    List<Category> findAllByRestaurant_IdOrderByDisplayOrderAsc(
            UUID restaurantId
    );

    List<Category> findAllByRestaurant_IdAndActiveTrueOrderByDisplayOrderAsc(
            UUID restaurantId
    );
}
