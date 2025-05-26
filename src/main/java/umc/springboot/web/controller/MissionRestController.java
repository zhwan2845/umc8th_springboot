package umc.springboot.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.springboot.apiPayload.ApiResponse;
import umc.springboot.converter.MissionConverter;
import umc.springboot.domain.Mission;
import umc.springboot.service.MissionService.MissionCommandService;
import umc.springboot.web.dto.MissionRequestDTO;
import umc.springboot.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.AddMissionResultDTO> addMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionRequestDTO.AddMissionDTO request) {

        Mission mission = missionCommandService.addMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toAddMissionResultDTO(mission));
    }

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<String> challengeMission(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionRequestDTO.ChallengeMissionDTO request) {

        missionCommandService.challengeMission(missionId, request);
        return ApiResponse.onSuccess("성공적으로 가게의 미션을 도전 중인 미션에 추가하였습니다.");
    }
}
