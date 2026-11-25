package com.cesul.barber.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserCreationDTO(
        @NotEmpty
        String name,
        @NotEmpty
        String email,
        String avatarUrl
){}

