package umc.springboot.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.domain.enums.MissionStatus;
import umc.springboot.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    Page<MemberMission> findAllByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);

    Optional<MemberMission> findByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);

    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);
}
