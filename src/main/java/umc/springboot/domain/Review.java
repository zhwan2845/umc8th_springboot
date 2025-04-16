package umc.springboot.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.springboot.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float score;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
