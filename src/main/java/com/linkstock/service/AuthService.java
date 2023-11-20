package com.linkstock.service;

import com.linkstock.dto.request.LogInRequestDTO;
import com.linkstock.dto.request.SignUpRequestDTO;
import com.linkstock.security.PrincipalUserDetails;
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

    /**
     * 로그인 메서드
     * @author : 박상희
     * @param logInRequestDTO : 로그인할 사용자 로그인 정보
     * @return - 로그인을 성공했을 경우 : 200
     * @return - 로그인을 실패했을 경우 : 500
     **/
    ResponseEntity<?> authenticate(final LogInRequestDTO logInRequestDTO);

    /**
     * 로그아웃
     * @author : 박상희
     * @param currentUserDetails : 로그인한 사용자 정보
     * @return - 200 : 로그아웃 성공
     * @return - 500 : 로그아웃 실패
     **/
    ResponseEntity<?> logout(final PrincipalUserDetails currentUserDetails);
}
