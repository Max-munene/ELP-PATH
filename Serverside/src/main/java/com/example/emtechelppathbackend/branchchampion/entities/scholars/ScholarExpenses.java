package com.example.emtechelppathbackend.branchchampion.entities.scholars;

import com.example.emtechelppathbackend.branchchampion.entities.Application;
/*import com.example.emtechelppathbackend.entities.Scholar;*/
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "expenses")
public class ScholarExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //   Why not use the school field in entity Application to fetch the school name?
//    private String schoolName;
    private Long form;
    private Long term;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    private Integer schoolFees;
    private Integer shopping;
    private Integer transport;
    private Integer upkeep;
    private Integer scholarTotalPerTerm;
    /*  @ManyToOne(fetch = FetchType.LAZY)
      @JsonIgnore
      @JoinColumn(name = "scholar_id")
      private Scholar scholar;*/
    @NotNull(message = "Each expense entry should be associated to a scholar")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "application_id")
    private Application application;

    public Integer getScholarTotalPerTerm() {
        return schoolFees + shopping + transport + upkeep;
    }

    public void setScholarTotalPerTerm(Integer scholarTotalPerTerm) {
        this.scholarTotalPerTerm = scholarTotalPerTerm;
    }
}
