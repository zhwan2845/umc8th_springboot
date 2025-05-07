package umc.springboot.repository.MissionRepository;

import umc.springboot.domain.Mission;
import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findOngoingMissions(Long memberId, Long cursor);

    List<Mission> findAvailableMissionsByRegion(String regionName, Long memberId, Long cursor);
}
