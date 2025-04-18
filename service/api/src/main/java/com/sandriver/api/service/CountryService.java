package com.sandriver.api.service;

import com.sandriver.api.dto.CountryRequestDto;
import com.sandriver.api.exception.ResourceNotFoundException;
import com.sandriver.api.mapper.CountryMapper;
import com.sandriver.api.model.Country;
import com.sandriver.api.model.Currency;
import com.sandriver.api.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findById(String id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", id));
    }

    public Country update(Country existingCountry, CountryRequestDto dto, Set<Currency> currencies) {
        Country updatedCountry = existingCountry.toBuilder()
                .name(dto.getName())
                .iso2(dto.getIso2())
                .iso3(dto.getIso3())
                .code(dto.getCode())
                .currencies(currencies)
                .build();

        return countryRepository.save(updatedCountry);
    }

    public Country save(Country country) {
        return countryRepository.save(country);
    }

    public Country saveFromDto(CountryRequestDto dto, Set<Currency> currencies) {
        Country country = CountryMapper.toEntity(dto, currencies);
        Country savedCountry = countryRepository.save(country);
        return savedCountry;
    }

    public void deleteById(String id) {
        if (!countryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Country", id);
        }
        countryRepository.deleteById(id);
    }

    public Set<Country> findAllByIds(Set<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Set.of();
        }
        return countryRepository.findAllById(ids).stream().collect(Collectors.toSet());
    }
}