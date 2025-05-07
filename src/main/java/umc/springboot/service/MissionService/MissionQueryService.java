package umc.springboot.service.MissionService;

import umc.springboot.domain.Mission;
import java.util.List;

public interface MissionQueryService {
    List<Mission> findOngoingMissions(Long memberId, Long cursor);

    List<Mission> findAvailableMissionsByRegion(String regionName, Long memberId, Long cursor);
}
