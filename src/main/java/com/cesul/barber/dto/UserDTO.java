package com.cesul.barber.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record UserDTO(
        @NotEmpty
        UUID id,
        @NotEmpty
        String name,
        @NotEmpty
        String email,
        String avatarUrl
) {}
