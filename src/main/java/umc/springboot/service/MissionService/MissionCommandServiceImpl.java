package umc.springboot.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.domain.Mission;
import umc.springboot.domain.Store;
import umc.springboot.repository.MissionRepository.MissionRepository;
import umc.springboot.repository.StoreRepository.StoreRepository;
import umc.springboot.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Mission createMission(Long storeId, MissionRequestDTO.MissionAddDto request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Mission mission = Mission.builder()
                .store(store)
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();

        return missionRepository.save(mission);
    }

}
