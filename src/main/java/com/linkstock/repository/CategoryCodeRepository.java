package com.linkstock.repository;

import com.linkstock.entity.CategoryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryCodeRepository extends JpaRepository<CategoryCode, String> {

    // 카테고리에 해당하는 종목 코드조회
    @Query("select distinct c.cCode from CategoryCode c where c.category = :category")
    List<String> cCodeList(@Param("category") String category);
}
