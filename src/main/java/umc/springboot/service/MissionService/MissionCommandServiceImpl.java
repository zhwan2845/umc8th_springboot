package umc.springboot.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.apiPayload.code.status.ErrorStatus;
import umc.springboot.apiPayload.exception.GeneralException;
import umc.springboot.apiPayload.exception.handler.StoreHandler;
import umc.springboot.converter.MissionConverter;
import umc.springboot.domain.Mission;
import umc.springboot.domain.Store;
import umc.springboot.repository.MissionRepository.MissionRepository;
import umc.springboot.repository.StoreRepository.StoreRepository;
import umc.springboot.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission addMission(Long storeId, MissionRequestDTO.AddMissionDTO request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request, store);
        return missionRepository.save(mission);
    }
}


