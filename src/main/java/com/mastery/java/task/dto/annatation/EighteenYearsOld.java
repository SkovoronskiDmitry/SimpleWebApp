package com.mastery.java.task.dto.annatation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EighteenYearsOldValidator.class)
public @interface EighteenYearsOld {
    String message() default "The Employee must be 18 years old.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
