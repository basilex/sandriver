package com.sandriver.api.controller;

import com.sandriver.api.dto.CountryRequestDto;
import com.sandriver.api.dto.CountryResponseDto;
import com.sandriver.api.mapper.CountryMapper;
import com.sandriver.api.model.Country;
import com.sandriver.api.model.Currency;
import com.sandriver.api.service.CountryService;
import com.sandriver.api.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final CurrencyService currencyService;

    @GetMapping
    public List<CountryResponseDto> getAllCountries() {
        return countryService.findAll().stream()
                .map(country -> CountryMapper.toResponse(country, true))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponseDto> getCountryById(@PathVariable String id) {
        return ResponseEntity.ok(CountryMapper.toResponse(countryService.findById(id), true));
    }

    @PostMapping
    public ResponseEntity<CountryResponseDto> createCountry(@Valid @RequestBody CountryRequestDto dto) {
        Set<Currency> currencies = currencyService.findAllByIds(dto.getCurrencyIds());
        Country savedCountry = countryService.saveFromDto(dto, currencies);
        return ResponseEntity.ok(CountryMapper.toResponse(savedCountry, true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponseDto> updateCountry(@PathVariable String id, @Valid @RequestBody CountryRequestDto dto) {
        Country existingCountry = countryService.findById(id);
        Set<Currency> currencies = currencyService.findAllByIds(dto.getCurrencyIds());
        Country updatedCountry = countryService.update(existingCountry, dto, currencies);
        return ResponseEntity.ok(CountryMapper.toResponse(updatedCountry, true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String id) {
        countryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}