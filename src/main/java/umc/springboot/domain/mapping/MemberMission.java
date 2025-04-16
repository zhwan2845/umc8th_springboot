package umc.springboot.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.springboot.domain.Member;
import umc.springboot.domain.Mission;
import umc.springboot.domain.common.BaseEntity;
import umc.springboot.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;
}
