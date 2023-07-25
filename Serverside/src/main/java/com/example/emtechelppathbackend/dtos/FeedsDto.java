package com.example.emtechelppathbackend.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedsDto {
        private Long id;
        private String description;
        private LocalDateTime postDate;

}
