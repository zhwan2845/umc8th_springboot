package umc.springboot.service.MemberService;

import org.springframework.data.domain.Page;
import umc.springboot.domain.Member;
import umc.springboot.domain.Review;

public interface MemberQueryService {
    Member findMemberById(Long memberId);

    Page<Review> getMyReviews(Long memberId, int page);
}
