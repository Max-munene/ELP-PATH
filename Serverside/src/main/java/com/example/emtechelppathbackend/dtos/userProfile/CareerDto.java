package com.example.emtechelppathbackend.dtos.userProfile;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CareerDto {
    private Long id;
    private String companyName;
    private String title;
    private String description;
    private LocalDate start_date;
    private LocalDate  end_date;

}
