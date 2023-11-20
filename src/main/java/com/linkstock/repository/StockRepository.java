package com.linkstock.repository;

import com.linkstock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {
    // 지정한 종목 리스트 중 등락률 기준 종목 데이터 조회

    // 지정한 종목 리스트 중 시가총액 기준 종목 데이터 조회
    @Query(value = "select s.stockCode, s.stockName, s.marketCap " +
            "from Stock s " +
            "where s.stockCode in :stockList " +
            "order by s.marketCap desc limit 20", nativeQuery = true)
    List<Object[]> findOrderByCap(@Param("stockList") List<String> stockList);
}
