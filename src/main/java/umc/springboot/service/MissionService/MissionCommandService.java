package umc.springboot.service.MissionService;

import umc.springboot.domain.Mission;
import umc.springboot.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Mission addMission(Long storeId, MissionRequestDTO.AddMissionDTO request);

    void challengeMission(Long missionId, MissionRequestDTO.ChallengeMissionDTO request);
}


