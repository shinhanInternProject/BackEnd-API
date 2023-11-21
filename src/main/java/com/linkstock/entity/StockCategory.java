package com.linkstock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 업종별 주식 정보를 나타내는 엔티티 클래스
 * @author : 김태우, 박상희
 **/
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_category")
@IdClass(StockCategoryId.class)
public class StockCategory implements Serializable {
    @Id
    @Column(name = "category_code", nullable = false)
    private String categoryCode; // 업종 코드

    @Id
    @Column(name = "stock_code", nullable = false)
    private String stockCode; // 종목 코드
}

class StockCategoryId implements Serializable {
    private String categoryCode;
    private String stockCode;
}
