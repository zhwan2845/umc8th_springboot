package umc.springboot.repository.MissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.JPAExpressions;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.springboot.domain.Mission;
import umc.springboot.domain.QMission;
import umc.springboot.domain.QRegion;
import umc.springboot.domain.QStore;
import umc.springboot.domain.mapping.QMemberMission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Mission> findOngoingMissions(Long memberId, Long cursor) {
        // 기존 구현
        return null;
    }

    // findAvailableMissionsByRegion 메서드 구현
    @Override
    public List<Mission> findAvailableMissionsByRegion(String regionName, Long memberId, Long cursor) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QRegion region = QRegion.region;
        QMemberMission memberMission = QMemberMission.memberMission;

        return queryFactory
                .select(mission)
                .from(mission)
                .join(mission.store, store).fetchJoin()
                .join(store.region, region)
                .where(
                        region.name.eq(regionName),
                        mission.deadline.goe(LocalDate.now()), // LocalDate.now()를 사용하여 비교
                        mission.id.lt(cursor),
                        mission.id.notIn(
                                JPAExpressions
                                        .select(memberMission.mission.id)
                                        .from(memberMission)
                                        .where(memberMission.member.id.eq(memberId))
                        )
                )
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }
}
