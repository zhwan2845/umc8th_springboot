package umc.springboot.repository.MemberRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.springboot.domain.Member;
import umc.springboot.domain.QMember;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Member findMemberById(Long memberId) {
        QMember member = QMember.member;

        return queryFactory
                .selectFrom(member)
                .where(member.id.eq(memberId))  // memberId와 일치하는 회원 조회
                .fetchOne();  // 한 건만 반환
    }
}
