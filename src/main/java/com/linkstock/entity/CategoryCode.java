package com.linkstock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 업종 코드 정보를 나타내는 엔티티 클래스
 * @author : 김태우, 박상희
 **/
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category_code")
@IdClass(CategoryCodeId.class)
public class CategoryCode implements Serializable {
    @Id
    @Column(name = "c_code", nullable = false)
    private String cCode; // 업종 코드

    @Id
    @Column(name = "category", nullable = false)
    private String category; // 업종
}

class CategoryCodeId implements Serializable {
    private String cCode;
    private String category;

    // 생성자, equals, hashCode 등 필요한 메서드 작성
}
