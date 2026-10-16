
package com.restaurantsaas.backend.controller;

import com.restaurantsaas.backend.dto.response.RestaurantResponse;
import com.restaurantsaas.backend.entity.Restaurant;
import com.restaurantsaas.backend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restaurantsaas.backend.dto.request.RestaurantCreateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/public/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/{slug}")
    public ResponseEntity<RestaurantResponse> findBySlug(
            @PathVariable String slug) {

        return restaurantService.findBySlug(slug)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<RestaurantResponse> create(
            @Valid @RequestBody RestaurantCreateRequest request) {

        Restaurant restaurant = restaurantService.create(
                request.name(),
                request.slug()
        );

        RestaurantResponse response = toResponse(restaurant);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private RestaurantResponse toResponse(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getSlug()
        );
    }
}
