package com.linkstock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @Column(name = "stock_code", nullable = false)
    private String stockCode; // 종목 코드

    @Column(name = "stock_name", nullable = false)
    private String stockName; // 종목 이름

    @Column(name = "day_range", nullable = false)
    private String dayRange; // 종목 등락률

    @Column(name = "market_cap", nullable = false)
    private String marketCap; // 종목 시가총액

    @Column(name = "update_date", nullable = false)
    private String updateDate; // 업데이트 날짜
}
