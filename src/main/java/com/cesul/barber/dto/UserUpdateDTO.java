package com.cesul.barber.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserUpdateDTO (
    @NotEmpty
    String name,
    @NotEmpty
    String email,
    String avatarUrl
){}
