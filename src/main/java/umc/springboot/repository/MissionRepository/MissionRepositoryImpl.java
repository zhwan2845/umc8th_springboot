package umc.springboot.repository.MissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.JPAExpressions;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.springboot.domain.Mission;
import umc.springboot.domain.QMission;
import umc.springboot.domain.QMember;
import umc.springboot.domain.QRegion;
import umc.springboot.domain.QStore;
import umc.springboot.domain.enums.MissionStatus;
import umc.springboot.domain.mapping.QMemberMission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Mission> findOngoingMissions(Long memberId, Long cursor) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QMemberMission memberMission = QMemberMission.memberMission;

        return queryFactory
                .select(mission)
                .from(mission)
                .join(mission.memberMissionList, memberMission)
                .on(memberMission.member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.CHALLENGING))  // "진행중" 상태인 미션만
                .join(mission.store, store).fetchJoin()
                .where(
                        mission.id.lt(cursor),  // 커서를 기준으로 이전 미션만
                        mission.id.notIn(
                                JPAExpressions
                                        .select(memberMission.mission.id)
                                        .from(memberMission)
                                        .where(memberMission.member.id.eq(memberId))
                        )  // 해당 회원이 참여한 미션을 제외
                )
                .orderBy(mission.id.desc())  // 최신 미션부터 조회
                .limit(10)  // 최대 10개의 미션을 조회
                .fetch();
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
                    region.name.eq(regionName),  // 특정 지역에 해당하는 미션만
                    mission.id.lt(cursor),        // 커서를 기준으로 이전 미션만
                    mission.id.notIn(
                            JPAExpressions
                                    .select(memberMission.mission.id)
                                    .from(memberMission)
                                    .where(memberMission.member.id.eq(memberId))
                    )  // 해당 회원이 참여한 미션을 제외
                )

//                .where(
//                        region.name.eq(regionName),
//                        mission.deadline.goe(LocalDate.now()), // LocalDate.now()를 사용하여 비교
//                        mission.id.lt(cursor),
//                        mission.id.notIn(
//                                JPAExpressions
//                                        .select(memberMission.mission.id)
//                                        .from(memberMission)
//                                        .where(memberMission.member.id.eq(memberId))
//                        )
//                )

                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }
}
