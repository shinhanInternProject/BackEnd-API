package com.linkstock.service;

import com.linkstock.dto.response.ResponseDTO;
import com.linkstock.entity.Card;
import com.linkstock.entity.CardHistory;
import com.linkstock.repository.CardHistoryRepository;
import com.linkstock.repository.CardRepository;
import com.linkstock.security.PrincipalUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    private final CardHistoryRepository cardHistoryRepository;

    /**
     * 현재 로그인한 사용자의 카드 정보 조회 메서드
     * @author : 박상희
     * @param currentUserDetails : 현재 로그인한 사용자 정보
     * @return - 카드 정보 조회에 성공했을 경우 : 200
     * @return - 로그인하지 않았을 경우 : 403
     * @return - 카드 정보 조회에 실패했을 경우 : 500
     */
    @Override
    public ResponseEntity<?> getCardInformation(final PrincipalUserDetails currentUserDetails) {
        try {
            if (currentUserDetails != null) { // 현재 로그인한 사용자가 있을 경우
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

    /**
     * 사용자 고유 번호와 카드 고유 번호를 기준으로 찾은 카드가 해당 사용자의 카드인지 확인하는 메서드
     * @author : 박상희
     * @param userSeq : 사용자 고유 번호
     * @param cardSeq : 카드 고유 번호
     * @return - 해당 사용자가 카드 소유자일 경우 : true
     * @return - 해당 사용자가 카드 소유자가 아닐 경우 : false
     **/
    public boolean checkCardUser(Long userSeq, Long cardSeq) {
        return cardRepository.existsByUserUserSeqAndCardSeq(userSeq, cardSeq);
    }

    /**
     * 특정 카드의 해당 월의 카드 내역을 조회하는 메서드
     * @author : 박상희
     * @param currentUserDetails : 현재 로그인한 사용자 정보
     * @param cardSeq : 카드 고유 번호
     * @param month : 월
     * @return - 카드 내역 조회에 성공했을 경우 : 200 - 카드 내역이 없을 경우 null return
     * @return - 카드 내역 조회에 성공했을 경우 : 200 - 카드 내역이 있을 경우 내림차순으로 정렬된 카드 내역 return
     * @return - 현재 로그인한 사용자와 카드 소유자가 다를 경우 : 401
     * @return - 로그인하지 않았을 경우 : 403
     * @return - 카드 내역 조회에 실패했을 경우 : 500
     */
    @Override
    public ResponseEntity<?> getMonthCardHistory(final PrincipalUserDetails currentUserDetails, final Long cardSeq, final int month) {
        try {
            if (currentUserDetails != null) { // 현재 로그인한 사용자가 있을 경우
                if (!checkCardUser(currentUserDetails.getUserSeq(), cardSeq)) { // 현재 로그인한 사용자와 카드 소유자가 다를 경우
                    ResponseDTO responseDTO = ResponseDTO.builder()
                            .message("접근 권한이 없습니다.")
                            .build();

                    return ResponseEntity
                            .status(HttpStatus.UNAUTHORIZED) // 401 Error
                            .body(responseDTO);
                }

                List<CardHistory> cardHistoryList = cardHistoryRepository.findAllByCardCardSeqAAndMonth(cardSeq, month);

                if (cardHistoryList.isEmpty()) { // month 월 카드 내역이 없을 경우
                    ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                            .message(month + " 월 카드 내역이 없습니다.")
                            .build();

                    return ResponseEntity.ok().body(responseDTO);
                }

                // month 월 카드 내역이 있을 경우
                int monthPrice = cardHistoryList.stream()
                        .mapToInt(CardHistory::getPaymentPrice)
                        .sum();

                Map<String, Integer> categoryMap = cardHistoryList.stream() // 카테고리별 총 소비 금액을 계산하여 Map으로 그룹화
                        .collect(Collectors.groupingBy(CardHistory::getPaymentCategory,
                                Collectors.summingInt(CardHistory::getPaymentPrice)));

                List<Map<String, String>> consumptionList = categoryMap.entrySet().stream()
                        .map(entry -> { // 카테고리와 카테고리별 총 소비 금액을 Map으로 변환
                            Map<String, String> map = new HashMap<>();
                            map.put("category", entry.getKey());
                            map.put("categoryPrice", String.valueOf(entry.getValue()));

                            return map;
                        })
                        .sorted((map1, map2) -> { // 카테고리별 총 소비 금액을 정수로 변환하여 내림차순 정렬
                            int price1 = Integer.parseInt(map1.get("categoryPrice"));
                            int price2 = Integer.parseInt(map2.get("categoryPrice"));

                            return Integer.compare(price2, price1);
                        })
                        .collect(Collectors.toList());

                ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                        .data(Map.of("monthPrice", monthPrice, "consumption", consumptionList))
                        .message(month + " 월의 소비 내역을 가져왔습니다.")
                        .build();

                return ResponseEntity.ok().body(responseDTO);
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED) // 401 Error
                        .body("카드 내역을 조회하기 전, 로그인이 필요합니다.");
            }
        }
        catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .message("소비 내역을 가져오지 못했습니다. " + e.getMessage())
                    .build();

            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(responseDTO);
        }
    }
}
