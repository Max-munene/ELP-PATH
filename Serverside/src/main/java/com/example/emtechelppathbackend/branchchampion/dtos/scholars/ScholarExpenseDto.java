package com.example.emtechelppathbackend.branchchampion.dtos.scholars;

import com.example.emtechelppathbackend.branchchampion.entities.Application;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ScholarExpenseDto {
    private Long id;
    private Long form;
    private Long term;
    private String schoolName;
    private LocalDate start_date;
    private Integer schoolFees;
    private Integer shopping;
    private Integer transport;
    private Integer upkeep;
    private Integer total;
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Application application;
}
