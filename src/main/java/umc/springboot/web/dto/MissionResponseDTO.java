package umc.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MissionResponseDTO {

    @Getter
    @Builder
    public static class AddMissionResultDTO {
        private Long missionId;
        private LocalDate createdAt;
    }
}
