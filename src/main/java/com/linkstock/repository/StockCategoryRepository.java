package com.linkstock.repository;

import com.linkstock.entity.StockCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockCategoryRepository extends JpaRepository<StockCategory,String> {

    // 업종코드 리스트에 해당하는 종목 코드 조회
    @Query("select s.stockCode from StockCategory s where s.categoryCode in :categoryCodeList")
    List<String> findByCategoryCodeList(@Param("categoryCodeList") List<String> categoryCodeList);
}
