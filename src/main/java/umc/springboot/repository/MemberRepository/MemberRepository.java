package umc.springboot.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
