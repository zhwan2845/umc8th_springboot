package umc.springboot.repository.MemberRepository;

import umc.springboot.domain.Member;

public interface MemberRepositoryCustom {
    Member findMemberById(Long memberId);
}
