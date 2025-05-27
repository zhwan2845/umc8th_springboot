package umc.springboot.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.apiPayload.code.status.ErrorStatus;
import umc.springboot.apiPayload.exception.handler.MemberHandler;
import umc.springboot.domain.Member;
import umc.springboot.domain.Review;
import umc.springboot.domain.enums.MissionStatus;
import umc.springboot.domain.mapping.MemberMission;
import umc.springboot.repository.MemberRepository.MemberRepository;
import umc.springboot.repository.MissionRepository.MemberMissionRepository;
import umc.springboot.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findMemberById(memberId);
    }

    @Override
    public Page<Review> getMyReviews(Long memberId, int page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }

    @Override
    public Page<MemberMission> getChallengingMissions(Long memberId, int page) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }

        return memberMissionRepository.findAllByMemberIdAndStatus(
                memberId,
                MissionStatus.CHALLENGING,
                PageRequest.of(page, 10)
        );
    }
}
