package com.restaurantsaas.backend.dto.response;

import java.util.UUID;

public record RestaurantResponse(
        UUID id,
        String name,
        String slug
) {
}