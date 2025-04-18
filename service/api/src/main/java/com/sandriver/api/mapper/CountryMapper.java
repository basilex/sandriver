package com.sandriver.api.mapper;

import com.sandriver.api.dto.CountryRequestDto;
import com.sandriver.api.dto.CountryResponseDto;
import com.sandriver.api.model.Country;
import com.sandriver.api.model.Currency;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class CountryMapper {

    public Country toEntity(CountryRequestDto dto, Set<Currency> currencies) {
        return Country.builder()
                .name(dto.getName())
                .iso2(dto.getIso2())
                .iso3(dto.getIso3())
                .code(dto.getCode())
                .currencies(currencies) // Связанные валюты
                .build();
    }

    public CountryResponseDto toResponse(Country country, boolean includeCurrencies) {
        return CountryResponseDto.builder()
                .id(country.getId())
                .name(country.getName())
                .iso2(country.getIso2())
                .iso3(country.getIso3())
                .code(country.getCode())
                .currencies(includeCurrencies && country.getCurrencies() != null
                        ? country.getCurrencies().stream()
                        .map(currency -> CurrencyMapper.toResponse(currency, false))
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }
}