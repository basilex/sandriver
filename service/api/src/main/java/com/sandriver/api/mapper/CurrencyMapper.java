package com.sandriver.api.mapper;

import com.sandriver.api.dto.CurrencyRequestDto;
import com.sandriver.api.dto.CurrencyResponseDto;
import com.sandriver.api.model.Country;
import com.sandriver.api.model.Currency;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class CurrencyMapper {

    public Currency toEntity(CurrencyRequestDto dto, Set<Country> countries) {
        return Currency.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .symbol(dto.getSymbol())
                .countries(countries) // Связанные страны
                .build();
    }

    public CurrencyResponseDto toResponse(Currency currency, boolean includeCountries) {
        return CurrencyResponseDto.builder()
                .id(currency.getId())
                .name(currency.getName())
                .code(currency.getCode())
                .symbol(currency.getSymbol())
                .countries(includeCountries && currency.getCountries() != null
                        ? currency.getCountries().stream()
                        .map(country -> CountryMapper.toResponse(country, false))
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }
}