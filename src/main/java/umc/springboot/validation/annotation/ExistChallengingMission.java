package umc.springboot.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.springboot.validation.validator.ChallengingMissionExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ChallengingMissionExistValidator.class)
@Target({ElementType.TYPE}) // DTO 클래스 전체에 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistChallengingMission {
    String message() default "이미 도전 중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
