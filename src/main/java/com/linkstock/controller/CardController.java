package com.linkstock.controller;

import com.linkstock.security.PrincipalUserDetails;
import com.linkstock.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    /**
     * 현재 로그인한 사용자의 카드 정보 조회 메서드
     * @author : 박상희
     * @param currentUserDetails : 현재 로그인한 사용자 정보
     * @return 현재 사용자의 카드 정보를 포함한 ResponseEntity
     **/
    @GetMapping()
    public ResponseEntity<?> signUp(@AuthenticationPrincipal PrincipalUserDetails currentUserDetails) {
        return cardService.getCardInformation(currentUserDetails);
    }
}
