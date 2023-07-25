/*
package com.example.emtechelppathbackend.branchchampion.entities.scholars;

import com.example.emtechelppathbackend.entities.Scholar;
import com.example.emtechelppathbackend.entities.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import java.util.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scholarApplication")
public class ScholarApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 1000)

    private Long scholar_id;
    private Status awardStatus;
    private Date applicationDate;
    private Date awardedDate;
    private String branch;
    private Date interviewDate;
    //list of uploads
    //private List <> uploads;


    */
/*

    // an application can be linked to only one scholar
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scholar_id", referencedColumnName = "id")
    private Scholar scholar;

    //an application can only be linked to one interview
    *//*

}
*/
