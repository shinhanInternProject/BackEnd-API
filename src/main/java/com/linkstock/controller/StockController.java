package com.linkstock.controller;

import com.linkstock.dto.response.ResponseDTO;
import com.linkstock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/link/stock")
public class StockController {
    private final StockService stockService;

    /**
     * 업종 종목 리스트(등락률) 메서드
     * @author 김태우
     * @param category : 조회하고자 하는 업종 코드
     * @return 업종에 관련된 종목 코드 리스트 등락률 기준 20개
     */
    @GetMapping("/earning/{category}")
    public ResponseEntity<?> categoryEarningList(@PathVariable(value = "category") String category) {
        return null;
    }

    /**
     * 업종 종목 리스트(시가총액) 메서드
     * @author 김태우
     * @param category : 조회하고자 하는 업종 코드
     * @return 업종에 관련된 종목 코드 리스트 시가총액 기준 20개
     */
    @GetMapping("/cap/{category}")
    public ResponseEntity<?> categoryCapList(@PathVariable(value = "category") String category) {
        try { // 정상 결과
            List<Object[]> result = stockService.stockListCap(category); // 카테고리에 해당하는 종목 - 시가총액 순
            return ResponseEntity.ok().body(result);
        } catch (Exception e) { // S3 정보 없음을 제외한 오류
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message("일치하는 정보 없음.").build();
            return ResponseEntity
                    .internalServerError()
                    .body(responseDTO);
        }
    }

}
