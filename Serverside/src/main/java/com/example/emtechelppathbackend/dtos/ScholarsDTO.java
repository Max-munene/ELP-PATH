package com.example.emtechelppathbackend.dtos;


import com.example.emtechelppathbackend.entities.Status;
import lombok.Data;

@Data
public class ScholarsDTO {
    private Long   id;
    private Long scholarNumber;
    private String name;
    private Status status;
    private String yearOfApplication;
    private String branch;
}
