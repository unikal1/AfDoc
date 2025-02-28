package com.afdoc.domain.memberManager.service;

import com.afdoc.domain.authManager.dto.AuthApiDto;

public interface MemberService {

    Long createMember(AuthApiDto.Register dto);
}
