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
    @Query(value = "select s.stock_code, s.stock_name, s.market_cap from stock s where s.stock_code in :stockList order by s.market_cap desc limit 20", nativeQuery = true)
    List<String[]> findOrderByCap(@Param("stockList") List<String> stockList);
}
