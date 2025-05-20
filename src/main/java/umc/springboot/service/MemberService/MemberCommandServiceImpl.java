package umc.springboot.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springboot.apiPayload.exception.handler.FoodCategoryHandler;
import umc.springboot.converter.MemberConverter;
import umc.springboot.converter.MemberPreferConverter;
import umc.springboot.domain.FoodCategory;
import umc.springboot.apiPayload.code.status.ErrorStatus;
import umc.springboot.domain.Member;
import umc.springboot.domain.mapping.MemberPrefer;
import umc.springboot.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.springboot.repository.MemberRepository.MemberRepository;
import umc.springboot.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}