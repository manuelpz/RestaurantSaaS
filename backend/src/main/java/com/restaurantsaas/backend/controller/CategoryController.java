
package com.restaurantsaas.backend.controller;

import com.restaurantsaas.backend.dto.request.CategoryCreateRequest;
import com.restaurantsaas.backend.dto.response.CategoryResponse;
import com.restaurantsaas.backend.entity.Category;
import com.restaurantsaas.backend.service.CategoryService;
import com.restaurantsaas.backend.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final RestaurantService restaurantService;

    @PostMapping("/admin/restaurants/{restaurantId}/categories")
    public ResponseEntity<CategoryResponse> create(
            @PathVariable UUID restaurantId,
            @Valid @RequestBody CategoryCreateRequest request) {

        Category category = categoryService.create(
                restaurantId,
                request.name(),
                request.displayOrder()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(category));
    }

    @GetMapping("/public/restaurants/{slug}/categories")
    public ResponseEntity<List<CategoryResponse>> findActiveByRestaurant(
            @PathVariable String slug) {

        return restaurantService.findBySlug(slug)
                .map(restaurant -> categoryService
                        .findActiveByRestaurant(restaurant.getId())
                        .stream()
                        .map(this::toResponse)
                        .toList())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getRestaurant().getId(),
                category.getName(),
                category.getDisplayOrder(),
                category.getActive()
        );
    }
}
