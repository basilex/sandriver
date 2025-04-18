package com.sandriver.api.model;

import com.sandriver.api.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder(toBuilder = true) // Для обновления с использованием билдера
@NoArgsConstructor
@AllArgsConstructor
public class Currency extends BaseEntity {

    @NotNull(message = "Name cannot be null")
    @Size(max = 255, message = "Name must be less than 255 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Code cannot be null")
    @Size(max = 10, message = "Code must be less than 10 characters")
    @Column(nullable = false, unique = true)
    private String code;

    @NotNull(message = "Symbol cannot be null")
    @Size(max = 10, message = "Symbol must be less than 10 characters")
    @Column(nullable = false)
    private String symbol;

    @ManyToMany(mappedBy = "currencies", fetch = FetchType.LAZY)
    private Set<Country> countries;
}