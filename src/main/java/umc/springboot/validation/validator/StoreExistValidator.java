package umc.springboot.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springboot.repository.StoreRepository.StoreRepository;
import umc.springboot.service.StoreService.StoreQueryService;
import umc.springboot.validation.annotation.ExistStore;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreQueryService storeQueryService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return false;
        // StoreQueryService의 findStore를 사용해서 존재 여부 확인
        return storeQueryService.findStore(value).isPresent();
    }
}