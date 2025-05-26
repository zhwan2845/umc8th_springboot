package umc.springboot.converter;

import umc.springboot.domain.Member;
import umc.springboot.domain.Mission;
import umc.springboot.domain.enums.MissionStatus;
import umc.springboot.domain.mapping.MemberMission;

public class MemberMissionConverter {
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
