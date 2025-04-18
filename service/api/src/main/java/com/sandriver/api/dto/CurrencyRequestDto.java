package com.sandriver.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class CurrencyRequestDto {

    @NotNull(message = "Name cannot be null")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @NotNull(message = "Code cannot be null")
    @Size(max = 10, message = "Code must be less than 10 characters")
    private String code;

    @NotNull(message = "Symbol cannot be null")
    @Size(max = 10, message = "Symbol must be less than 10 characters")
    private String symbol;

    private Set<String> countryIds;
}