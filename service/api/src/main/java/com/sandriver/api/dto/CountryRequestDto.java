package com.sandriver.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class CountryRequestDto {

    @NotNull(message = "Name cannot be null")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @NotNull(message = "ISO2 code cannot be null")
    @Size(max = 2, message = "ISO2 code must be exactly 2 characters")
    private String iso2;

    @NotNull(message = "ISO3 code cannot be null")
    @Size(max = 3, message = "ISO3 code must be exactly 3 characters")
    private String iso3;

    @NotNull(message = "Code cannot be null")
    @Size(max = 10, message = "Code must be less than 10 characters")
    private String code;

    private Set<String> currencyIds;
}