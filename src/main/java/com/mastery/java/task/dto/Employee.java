package com.mastery.java.task.dto;

import com.mastery.java.task.dto.annatation.EighteenYearsOld;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@ApiModel(value = "Employee Model", subTypes = {Employee.class}, description = "POJO class for Swagger documentation")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id can't be null")
    @Positive(message = "Only positive number")
    private Long employeeId;
    @NotEmpty(message = "The FirstName value can't be empty")
    private String firstName;
    @NotEmpty(message = "The LastName value can't be empty")
    private String lastName;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "The Gender value can't be empty")
    private Gender gender;
    @Positive(message = "Only positive number")
    @NotNull(message = "The Department ID value can't be null")
    private Long departmentId;
    @NotEmpty(message = "The Job Title value can't be empty")
    private String jobTitle;
    @EighteenYearsOld
    private LocalDate dateOfBirth;

}
