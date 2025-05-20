package umc.springboot.service.MemberService;

import umc.springboot.domain.Member;
import umc.springboot.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}