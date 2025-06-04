package umc.springboot.converter;

import org.springframework.data.domain.Page;
import umc.springboot.domain.Member;
import umc.springboot.domain.Review;
import umc.springboot.domain.enums.Gender;
import umc.springboot.domain.mapping.MemberMission;
import umc.springboot.web.dto.MemberRequestDTO;
import umc.springboot.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberResponseDTO.LoginResultDTO toLoginResultDTO(Long memberId, String accessToken) {
        return MemberResponseDTO.LoginResultDTO.builder()
                .memberId(memberId)
                .accessToken(accessToken)
                .build();
    }

    public static MemberResponseDTO.MemberInfoDTO toMemberInfoDTO(Member member){
        return MemberResponseDTO.MemberInfoDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .gender(member.getGender().name())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {
        Gender gender = null;
        switch (request.getGender()) {
            case 1: gender = Gender.MALE; break;
            case 2: gender = Gender.FEMALE; break;
            case 3: gender = Gender.NONE; break;
        }

        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .gender(gender)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .role(request.getRole())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    // 내가 작성한 리뷰 목록 조회
    public static MemberResponseDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return MemberResponseDTO.MyReviewDTO.builder()
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.MyReviewListDTO toMyReviewListDTO(Page<Review> reviewList) {
        List<MemberResponseDTO.MyReviewDTO> content = reviewList.stream()
                .map(MemberConverter::toMyReviewDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.MyReviewListDTO.builder()
                .reviewList(content)
                .listSize(content.size())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .build();
    }

    public static MemberResponseDTO.ChallengingMissionDTO toChallengingMissionDTO(MemberMission memberMission) {
        return MemberResponseDTO.ChallengingMissionDTO.builder()
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .storeName(memberMission.getMission().getStore().getName())
                .build();
    }

    public static MemberResponseDTO.ChallengingMissionListDTO toChallengingMissionListDTO(Page<MemberMission> page) {
        List<MemberResponseDTO.ChallengingMissionDTO> content = page.getContent().stream()
                .map(MemberConverter::toChallengingMissionDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.ChallengingMissionListDTO.builder()
                .missionList(content)
                .listSize(content.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}