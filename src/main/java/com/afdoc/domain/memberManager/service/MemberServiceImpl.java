package com.afdoc.domain.memberManager.service;

import com.afdoc.domain.authManager.dto.AuthApiDto;
import com.afdoc.domain.memberManager.dao.MemberRepository;
import com.afdoc.domain.memberManager.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public Long createMember(AuthApiDto.Register dto) {
        Member member = Member.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .imgUrl(dto.getImgUrl())
                .build();

        memberRepository.save(member);
        return member.getId();
    }
}
