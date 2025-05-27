package umc.springboot.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.springboot.validation.validator.CheckPageValidator;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CheckPageValidator.class)
public @interface CheckPage {
    String message() default "page는 1 이상의 숫자여야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

