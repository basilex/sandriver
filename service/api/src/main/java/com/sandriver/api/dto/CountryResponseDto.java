package com.sandriver.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CountryResponseDto {

    private String id;
    private String name;
    private String iso2;
    private String iso3;
    private String code;

    private Set<CurrencyResponseDto> currencies;
}