package umc.springboot.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.domain.Mission;
import umc.springboot.repository.MissionRepository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public List<Mission> findOngoingMissions(Long memberId, Long cursor) {
        List<Mission> ongoingMissions = missionRepository.findOngoingMissions(memberId, cursor);
        ongoingMissions.forEach(mission -> System.out.println("Mission: " + mission));
        return ongoingMissions;
    }

    // findAvailableMissionsByRegion 메서드 추가
    @Override
    public List<Mission> findAvailableMissionsByRegion(String regionName, Long memberId, Long cursor) {
        return missionRepository.findAvailableMissionsByRegion(regionName, memberId, cursor);
    }
}
