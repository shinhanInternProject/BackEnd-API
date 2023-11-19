package com.linkstock.service;

import com.linkstock.dto.request.SignUpRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    /**
     * 해당 이메일이 존재하는지 체크하는 메서드
     * @author : 박상희
     * @param email : 존재 여부를 검사할 이메일
     * @return - 해당 이메일이 존재하지 않을 경우 : 200
     * @return - 해당 이메일이 존재할 경우 : 500
     */
    ResponseEntity<?> checkEmail(final String email);

    /**
     * 회원 가입 메서드
     * @author : 박상희
     * @param signUpRequestDTO : 회원 가입할 사용자 회원 가입 정보
     * @return - 회원 가입에 성공했을 경우 : 200
     * @return - 회원 가입에 실패했을 경우 : 500
     **/
    ResponseEntity<?> registerUser(final SignUpRequestDTO signUpRequestDTO);
}
