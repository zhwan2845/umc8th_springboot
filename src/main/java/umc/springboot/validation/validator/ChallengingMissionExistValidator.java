// ChallengingMissionExistValidator.java
package umc.springboot.validation.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springboot.repository.MissionRepository.MemberMissionRepository;
import umc.springboot.validation.annotation.ExistChallengingMission;
import umc.springboot.web.dto.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class ChallengingMissionExistValidator implements ConstraintValidator<ExistChallengingMission, MissionRequestDTO.ChallengeMissionDTO> {

    private final MemberMissionRepository memberMissionRepository;
    private final HttpServletRequest request; // missionId 추출용

    @Override
    public boolean isValid(MissionRequestDTO.ChallengeMissionDTO dto, ConstraintValidatorContext context) {
        if (dto.getMemberId() == null) return false;

        Long missionId = extractMissionId(request.getRequestURI());
        if (missionId == null) return false;

        boolean exists = memberMissionRepository.existsByMemberIdAndMissionId(dto.getMemberId(), missionId);

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 도전 중인 미션입니다.").addConstraintViolation();
        }

        return !exists;
    }

    private Long extractMissionId(String uri) {
        try {
            String[] parts = uri.split("/");
            return Long.parseLong(parts[2]); // missions/{id}/challenge → 2번째 인덱스
        } catch (Exception e) {
            return null;
        }
    }
}
