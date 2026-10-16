
package com.restaurantsaas.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RestaurantCreateRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
        String name,

        @NotBlank(message = "El slug es obligatorio")
        @Size(max = 150, message = "El slug no puede superar los 150 caracteres")
        @Pattern(
                regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",
                message = "El slug solo admite letras minúsculas, números y guiones"
        )
        String slug

) {
}
