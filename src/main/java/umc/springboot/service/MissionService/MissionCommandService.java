package umc.springboot.service.MissionService;

import umc.springboot.domain.Mission;
import umc.springboot.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Mission createMission(Long storeId, MissionRequestDTO.MissionAddDto request);
}

