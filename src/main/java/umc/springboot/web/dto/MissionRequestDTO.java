package umc.springboot.web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class MissionAddDto {
        @NotNull
        private Integer reward;

        @NotNull
        private LocalDate deadline;

        @NotBlank
        private String missionSpec;
    }

    @Getter
    public static class MissionChallengeDto {
        @NotNull
        private Long memberId;

        @NotNull
        private Long missionId;
    }
}
