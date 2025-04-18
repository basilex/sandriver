package com.sandriver.api.model;

import com.sandriver.api.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
public class Country extends BaseEntity {

    @NotNull(message = "Name cannot be null")
    @Size(max = 255, message = "Name must be less than 255 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "ISO2 code cannot be null")
    @Size(max = 2, message = "ISO2 code must be exactly 2 characters")
    @Column(nullable = false, unique = true)
    private String iso2;

    @NotNull(message = "ISO3 code cannot be null")
    @Size(max = 3, message = "ISO3 code must be exactly 3 characters")
    @Column(nullable = false, unique = true)
    private String iso3;

    @NotNull(message = "Code cannot be null")
    @Size(max = 10, message = "Code must be less than 10 characters")
    @Column(nullable = false, unique = true)
    private String code;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "country_currency",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id")
    )
    private Set<Currency> currencies;
}