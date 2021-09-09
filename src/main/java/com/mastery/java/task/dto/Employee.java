package com.mastery.java.task.dto;

import com.mastery.java.task.dto.annatation.EighteenYearsOld;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;


@ApiModel(value = "Employee Model", subTypes = {Employee.class}, description = "POJO class for Swagger documentation")
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    public Employee(
            final String firstName,
            final String lastName,
            final Gender gender,
            final Long departmentId,
            final String jobTitle,
            final LocalDate dateOfBirth
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.dateOfBirth = dateOfBirth;
    }

    @ApiModelProperty(value = "Unique number id", example = "1", required = true, position = 1)
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(final Long employeeId) {
        this.employeeId = employeeId;
    }

    @ApiModelProperty(value = "Employee firstname", example = "John", required = true, position = 2)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @ApiModelProperty(value = "Employee lastname", example = "Smith", required = true, position = 3)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @ApiModelProperty(value = "Gender identity", allowableValues = "MALE, FEMALE", required = true, position = 5)
    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    @ApiModelProperty(value = "Employee's department number", example = "9", required = true, position = 6)
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(final Long departmentId) {
        this.departmentId = departmentId;
    }

    @ApiModelProperty(value = "Employee's job title", example = "boss", required = true, position = 7)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @ApiModelProperty(value = "Employee's birthday", example = "2007-12-25", required = true, position = 4)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId)
                && firstName.equals(employee.firstName)
                && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, departmentId, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
