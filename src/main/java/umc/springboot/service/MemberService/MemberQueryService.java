package umc.springboot.service.MemberService;

import umc.springboot.domain.Member;

public interface MemberQueryService {
    Member findMemberById(Long memberId);
}
