package com.linkstock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 업종 코드 정보를 나타내는 엔티티 클래스
 * @author : 박상희
 **/
@Data
@Entity
@Builder
@IdClass(CategoryCodeId.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category_code",
    indexes = {
        @Index(name = "idx_c_code", columnList = "c_code"),
        @Index(name = "idx_category", columnList = "category")
    }
)
public class CategoryCode implements Serializable {
    @Id
    @Column(name = "c_code", nullable = false)
    private String cCode; // 업종 코드

    @Id
    @Column(name = "category", nullable = false)
    private String category; // 업종
}
