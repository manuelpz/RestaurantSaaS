
package com.restaurantsaas.backend.dto.response;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        UUID restaurantId,
        String name,
        Integer displayOrder,
        Boolean active
) {
}
