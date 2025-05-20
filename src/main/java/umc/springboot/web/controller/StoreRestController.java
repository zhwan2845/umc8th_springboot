package umc.springboot.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.springboot.domain.Mission;
import umc.springboot.service.MissionService.MissionCommandService;
import umc.springboot.web.dto.MissionRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/missions")
    public ResponseEntity<String> addMissionToStore(
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid MissionRequestDTO.MissionAddDto request
    ) {
        Mission mission = missionCommandService.createMission(storeId, request);
        return ResponseEntity.ok("미션 생성 완료. ID: " + mission.getId());
    }
}
