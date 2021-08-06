package com.mastery.java.task.dto.annatation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EighteenYearsOldValidator implements ConstraintValidator<EighteenYearsOld, LocalDate> {
    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {

        if (dateOfBirth != null) {
            long year = ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
            return dateOfBirth.isBefore(LocalDate.now()) && year > 18;
        }
        return true;
    }
}
