package umc.springboot.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.springboot.apiPayload.ApiResponse;
import umc.springboot.converter.MemberConverter;
import umc.springboot.domain.Member;
import umc.springboot.domain.Review;
import umc.springboot.domain.mapping.MemberMission;
import umc.springboot.service.MemberService.MemberCommandService;
import umc.springboot.service.MemberService.MemberQueryService;
import umc.springboot.validation.annotation.CheckPage;
import umc.springboot.web.dto.MemberRequestDTO;
import umc.springboot.web.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/join")
    @Operation(summary = "유저 회원가입 API",description = "유저가 회원가입하는 API입니다.")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginRequestDTO request) {
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
            description = "유저가 내 정보를 조회하는 API입니다.",
            security = { @SecurityRequirement(name = "JWT TOKEN") }
    )
    public ApiResponse<MemberResponseDTO.MemberInfoDTO> getMyInfo(HttpServletRequest request) {
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }

    @GetMapping("/{memberId}/my-reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "로그인한 사용자가 작성한 리뷰 목록을 페이징 처리하여 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID"),
            @Parameter(name = "page", description = "페이지 번호 (1 이상)")
    })
    public ApiResponse<MemberResponseDTO.MyReviewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Review> reviews = memberQueryService.getMyReviews(memberId, page - 1);
        return ApiResponse.onSuccess(MemberConverter.toMyReviewListDTO(reviews));
    }

    @GetMapping("/{memberId}/missions/challenging")
    @Operation(summary = "내가 도전 중인 미션 목록 조회 API", description = "현재 도전 중인 미션들을 페이징 처리해서 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "존재하지 않는 멤버", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "page는 1 이상이어야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID (PathVariable)"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작, query param)")
    })
    public ApiResponse<MemberResponseDTO.ChallengingMissionListDTO> getChallengingMissions(
            @PathVariable Long memberId,
            @CheckPage @RequestParam("page") Integer page
    ) {
        Page<MemberMission> result = memberQueryService.getChallengingMissions(memberId, page - 1);
        return ApiResponse.onSuccess(MemberConverter.toChallengingMissionListDTO(result));
    }
}