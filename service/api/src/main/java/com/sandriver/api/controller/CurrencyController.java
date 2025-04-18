package com.sandriver.api.controller;

import com.sandriver.api.dto.CurrencyRequestDto;
import com.sandriver.api.dto.CurrencyResponseDto;
import com.sandriver.api.mapper.CurrencyMapper;
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
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CountryService countryService;

    @GetMapping
    public List<CurrencyResponseDto> getAllCurrencies() {
        return currencyService.findAll().stream()
                .map(currency -> CurrencyMapper.toResponse(currency, true))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> getCurrencyById(@PathVariable String id) {
        return ResponseEntity.ok(CurrencyMapper.toResponse(currencyService.findById(id), true));
    }

    @PostMapping
    public ResponseEntity<CurrencyResponseDto> createCurrency(@Valid @RequestBody CurrencyRequestDto dto) {
        Set<Country> countries = countryService.findAllByIds(dto.getCountryIds());
        Currency savedCurrency = currencyService.saveFromDto(dto, countries);
        return ResponseEntity.ok(CurrencyMapper.toResponse(savedCurrency, true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> updateCurrency(
            @PathVariable String id,
            @Valid @RequestBody CurrencyRequestDto dto
    ) {
        Currency existingCurrency = currencyService.findById(id);
        Set<Country> countries = countryService.findAllByIds(dto.getCountryIds());
        return ResponseEntity.ok(
                CurrencyMapper.toResponse(
                        currencyService.update(existingCurrency, dto, countries), true
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable String id) {
        currencyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}