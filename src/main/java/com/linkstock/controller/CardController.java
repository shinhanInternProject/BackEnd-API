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
    public ResponseEntity<?> getCardInformation(@AuthenticationPrincipal PrincipalUserDetails currentUserDetails) {
        return cardService.getCardInformation(currentUserDetails);
    }

    /**
     * 특정 카드의 해당 월의 카드 내역을 조회하는 메서드
     * @author : 박상희
     * @param currentUserDetails : 현재 로그인한 사용자 정보
     * @param cardSeq : 카드 고유 번호
     * @param month : 월
     * @return - 카드 내역 조회에 성공했을 경우 : 200 - 카드 내역이 없을 경우 null return
     * @return - 카드 내역 조회에 성공했을 경우 : 200 - 카드 내역이 있을 경우 카드 내역 return
     * @return - 로그인하지 않았을 경우 : 403
     * @return - 카드 내역 조회에 실패했을 경우 : 500
     **/
    @GetMapping("/{cardSeq}/consumption/{month}")
    public ResponseEntity<?> getMonthCardHistory(@AuthenticationPrincipal PrincipalUserDetails currentUserDetails,
                                                 @PathVariable Long cardSeq, @PathVariable int month) {
        return cardService.getMonthCardHistory(currentUserDetails, cardSeq, month);
    }

    /**
     * 특정 카드의 해당 월의 카테고리별 소비 내역을 조회하는 메서드
     * @author : 박상희
     * @param currentUserDetails : 현재 로그인한 사용자 정보
     * @param cardSeq : 카드 고유 번호
     * @param month : 월
     * @param category : 카테고리
     * @return - 카드 내역 조회에 성공했을 경우 : 200 - 카드 내역이 없을 경우 null return
     * @return - 카테고리별 소비 내역 조회에 성공했을 경우 : 200 - 카드 내역이 있을 경우 카테고리별 소비 내역 return
     * @return - 현재 로그인한 사용자와 카드 소유자가 다를 경우 : 401
     * @return - 로그인하지 않았을 경우 : 403
     * @return - 카테고리별 소비 내역이 없을 경우 : 500
     * @return - 카테고리별 소비 내역 조회에 실패했을 경우 : 500
     **/
    @GetMapping("/{cardSeq}/consumption/{month}/{category}")
    public ResponseEntity<?> getMonthCategoryCardHistory(@AuthenticationPrincipal PrincipalUserDetails currentUserDetails,
                                                         @PathVariable Long cardSeq, @PathVariable int month, @PathVariable String category) {
        return cardService.getMonthCategoryCardHistory(currentUserDetails, cardSeq, month, category);
    }
}
