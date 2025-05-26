package umc.springboot.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
