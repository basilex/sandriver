package com.sandriver.api.service;

import com.sandriver.api.dto.CurrencyRequestDto;
import com.sandriver.api.exception.ResourceNotFoundException;
import com.sandriver.api.mapper.CurrencyMapper;
import com.sandriver.api.model.Country;
import com.sandriver.api.model.Currency;
import com.sandriver.api.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency saveFromDto(CurrencyRequestDto dto, Set<Country> countries) {
        Currency currency = CurrencyMapper.toEntity(dto, countries);
        return currencyRepository.save(currency);
    }

    public Currency update(Currency existingCurrency, CurrencyRequestDto dto, Set<Country> countries) {
        Currency updatedCurrency = existingCurrency.toBuilder()
                .name(dto.getName())
                .code(dto.getCode())
                .symbol(dto.getSymbol())
                .countries(countries)
                .build();

        return currencyRepository.save(updatedCurrency); // Вызов @PreUpdate
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    public Currency findById(String id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currency not found with ID: " + id));
    }

    public void deleteById(String id) {
        if (!currencyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Currency", id);
        }
        currencyRepository.deleteById(id);
    }

    public Set<Currency> findAllByIds(Set<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Set.of();
        }
        return currencyRepository.findAllById(ids).stream().collect(Collectors.toSet());
    }
}