package com.linkstock.controller;

import com.linkstock.dto.request.LogInRequestDTO;
import com.linkstock.dto.request.SignUpRequestDTO;
import com.linkstock.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * 사용자가 입력한 사용자 이메일이 존재하는지 체크하는 메서드
     * @author : 박상희
     * @param emailMap : 사용자가 입력한 사용자 이메일
     * @return - 사용자가 입력한 사용자 이메일이 존재하지 않을 경우 : 200
     * @return - 사용자가 입력한 사용자 이메일이 존재할 경우 : 500
     **/
    @PostMapping("/emailcheck")
    public ResponseEntity<?> checkUserEmail(@RequestBody Map<String, String> emailMap) {
        String email = emailMap.get("email");

        return authService.checkEmail(email);
    }

    /**
     * 회원 가입 메서드
     * @author : 박상희
     * @param signUpRequestDTO : 사용자가 입력한 사용자 회원 가입 정보
     * @return - 회원 가입을 성공했을 경우 : 200
     * @return - 회원 가입을 실패했을 경우 : 500
     **/
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return authService.registerUser(signUpRequestDTO);
    }

    /**
     * 로그인 메서드
     * @author : 박상희
     * @param logInRequestDTO : 사용자가 입력한 사용자 로그인 정보
     * @return - 로그인을 성공했을 경우 : 200
     * @return - 로그인을 실패했을 경우 : 500
     **/
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LogInRequestDTO logInRequestDTO) {
        return authService.authenticate(logInRequestDTO);
    }
}
