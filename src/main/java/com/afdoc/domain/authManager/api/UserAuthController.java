package com.afdoc.domain.authManager.api;


import com.afdoc.domain.authManager.dto.AuthApiDto;
import com.afdoc.domain.authManager.service.AuthService;
import com.afdoc.domain.memberManager.service.MemberService;
import com.afdoc.global.security.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserAuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> signIn(@RequestBody AuthApiDto.SignIn dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        JwtToken token = authService.signInByUsernamePassword(username,password);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthApiDto.Register dto) {
        System.out.println("register : " + dto.getUsername());
        memberService.createMember(dto);
        return ResponseEntity.ok("success");
    }
}
