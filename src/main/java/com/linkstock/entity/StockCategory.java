package com.linkstock.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
