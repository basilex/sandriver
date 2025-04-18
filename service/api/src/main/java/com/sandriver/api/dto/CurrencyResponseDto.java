package com.sandriver.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CurrencyResponseDto {

    private String id;
    private String name;
    private String code;
    private String symbol;

    private Set<CountryResponseDto> countries;
}