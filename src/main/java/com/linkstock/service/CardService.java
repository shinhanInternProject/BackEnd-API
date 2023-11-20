package com.linkstock.service;

import com.linkstock.security.PrincipalUserDetails;
import org.springframework.http.ResponseEntity;

public interface CardService {
    /**
     * 현재 로그인한 사용자의 카드 정보 조회 메서드
     * @author : 박상희
     * @param currentUserDetails : 현재 로그인한 사용자 정보
     * @return - 카드 정보 조회에 성공했을 경우 : 200
     * @return - 로그인하지 않았을 경우 : 403
     * @return - 카드 정보 조회에 실패했을 경우 : 500
     */
    ResponseEntity<?> getCardInformation(PrincipalUserDetails currentUserDetails);
}
