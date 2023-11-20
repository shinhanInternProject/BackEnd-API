package com.linkstock.service;

import com.linkstock.dto.response.ResponseDTO;
import com.linkstock.entity.Card;
import com.linkstock.repository.CardRepository;
import com.linkstock.security.PrincipalUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    /**
     * 현재 로그인한 사용자의 카드 정보 조회 메서드
     * @author : 박상희
     * @param currentUserDetails : 현재 로그인한 사용자 정보
     * @return - 카드 정보 조회에 성공했을 경우 : 200
     * @return - 로그인하지 않았을 경우 : 403
     * @return - 카드 정보 조회에 실패했을 경우 : 500
     */
    @Override
    public ResponseEntity<?> getCardInformation(PrincipalUserDetails currentUserDetails) {
        try {
            if (currentUserDetails != null) {
                Card card = cardRepository.findByUserUserSeq(currentUserDetails.getUserSeq())
                        .orElseThrow(() -> new RuntimeException("카드 정보를 찾지 못했습니다."));

                ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                        .data(
                                Map.of(
                                        "cardSeq", card.getCardSeq(),
                                        "cardType", card.getCardType(),
                                        "cardName", card.getCardName()
                                )
                        )
                        .message(card.getCardName() + "의 카드 정보를 가져왔습니다.")
                        .build();

                return ResponseEntity.ok().body(responseDTO);
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED) // 401 Error
                        .body("카드 정보를 불러오기 전, 로그인이 필요합니다.");
            }
        }
        catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().message("카드 정보를 가져오지 못했습니다. " + e.getMessage()).build();

            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(responseDTO);
        }
    }
}
