package com.linkstock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_category")
@IdClass(StockCategoryId.class)
@Entity
public class StockCategory implements Serializable {
    @Id
    @Column(name = "category_code", nullable = false)
    private String categoryCode;

    @Id
    @Column(name = "stock_code", nullable = false)
    private String stockCode;

}
