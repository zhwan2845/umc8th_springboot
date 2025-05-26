package umc.springboot.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.springboot.validation.annotation.ExistChallengingMission;
import umc.springboot.validation.annotation.ExistStore;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    @NoArgsConstructor
    public static class AddMissionDTO {

        @NotNull
        private Integer reward;

        @NotNull
        private LocalDate deadline;

        @NotBlank
        private String missionSpec;
    }

    @Getter
    @NoArgsConstructor
    @ExistChallengingMission
    public static class ChallengeMissionDTO {
        @NotNull
        private Long memberId;
    }
}
