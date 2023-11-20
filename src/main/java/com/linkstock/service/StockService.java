package com.linkstock.service;

import com.linkstock.repository.CategoryCodeRepository;
import com.linkstock.repository.StockCategoryRepository;
import com.linkstock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockRepository stockRepository;
    private final CategoryCodeRepository categoryCodeRepository;
    private final StockCategoryRepository stockCategoryRepository;

    // 주식 종목 리스트에 해당하는 종목 - 시가총액 기준
    @Transactional
    public List<Object[]> stockListCap(String category) {
        // 카테고리에 해당하는 업종 코드 리스트
        List<String> cCodeList = categoryCodeRepository.cCodeList(category);
        // 업종 코드 리스트에 속하는 종목 코드 리스트
        List<String> stockList = stockCategoryRepository.findByCategoryCodeList(cCodeList);
        // 종목 코드 리스트에 해당하는 종목 코드들 -> 시총 기준 상위 20개 반환
        List<Object[]> result = stockRepository.findOrderByCap(stockList);

        return result;
    }

    // 주식 종목 리스트에 해당하는 종목 - 등락률 기준
}
