package umc.springboot.service.MemberService;

import umc.springboot.domain.Member;
import umc.springboot.web.dto.MemberRequestDTO;
import umc.springboot.web.dto.MemberResponseDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);

    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}