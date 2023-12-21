package com.anilerc.commandmicroservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductDto(@NotBlank String name, @Positive double price ) {}
