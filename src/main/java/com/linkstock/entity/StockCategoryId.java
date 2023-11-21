package com.linkstock.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class StockCategoryId implements Serializable {
    private String categoryCode;

    private String stockCode;
}
