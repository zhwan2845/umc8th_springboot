package umc.springboot.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.apiPayload.code.status.ErrorStatus;
import umc.springboot.apiPayload.exception.handler.MemberHandler;
import umc.springboot.apiPayload.exception.handler.MissionHandler;
import umc.springboot.apiPayload.exception.handler.StoreHandler;
import umc.springboot.converter.MemberMissionConverter;
import umc.springboot.converter.MissionConverter;
import umc.springboot.domain.Member;
import umc.springboot.domain.Mission;
import umc.springboot.domain.Store;
import umc.springboot.domain.mapping.MemberMission;
import umc.springboot.repository.MemberRepository.MemberRepository;
import umc.springboot.repository.MissionRepository.MemberMissionRepository;
import umc.springboot.repository.MissionRepository.MissionRepository;
import umc.springboot.repository.StoreRepository.StoreRepository;
import umc.springboot.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Mission addMission(Long storeId, MissionRequestDTO.AddMissionDTO request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request, store);
        return missionRepository.save(mission);
    }

    @Override
    @Transactional
    public void challengeMission(Long missionId, MissionRequestDTO.ChallengeMissionDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
        memberMissionRepository.save(memberMission);
    }
}
