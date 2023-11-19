package com.linkstock.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    /**
     * 해당 이메일이 존재하는지 체크하는 메서드
     * @author : 박상희
     * @param email : 존재 여부를 검사할 이메일
     * @return - 사용자가 입력한 사용자 이메일이 존재하지 않을 경우 : 200
     * @return - 사용자가 입력한 사용자 이메일이 존재할 경우 : 500
     */
    public ResponseEntity<?> checkEmail(final String email);
}
