package umc.springboot.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import umc.springboot.domain.Member;
import umc.springboot.domain.Review;
import umc.springboot.domain.mapping.MemberMission;
import umc.springboot.web.dto.MemberResponseDTO;

public interface MemberQueryService {
    Member findMemberById(Long memberId);

    Page<Review> getMyReviews(Long memberId, int page);

    Page<MemberMission> getChallengingMissions(Long memberId, int page);

    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
